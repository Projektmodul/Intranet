package com.example.application.backend.utils.images;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.ImageService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.InputStream;
import java.util.Date;


/**
 * This is class manages the upload and image components and uses the
 * imageCreationManager and the imageDeletionManager to create and delete images.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 6.0
 * @since   19.01.2021
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
public class ImageManager {

    private ImageService imageService;

    private ImageEntity imageEntity;
    private Image image;

    private ImageCreationManager imageCreationManager;
    private ImageDeletionManager imageDeletionManager;

    private Upload upload;
    private MemoryBuffer buffer;

    private InputStream inputStream;

    private Div uploaderContainer;

    private Div errorContainer;

    private boolean isOneImage;
    private boolean isImageUploaded = false;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private int role;

    private final String RESOURCES_DIR = "/home/dummyData/pictures/";

    public ImageManager(ImageService imageService, int role){
        this.imageService = imageService;
        this.role = role;
        isOneImage = true;

        initializeUploader();
    }

    public Image createImage(){
        image = new Image(imageEntity, inputStream, role);
        return image;
    }

    /**
     * This method initializes the Uploader with an upload button and a buffer to save the content of the uploaded file
     */
    private void initializeUploader(){
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);
        uploaderContainer = new Div();

        errorContainer = new Div();
        uploaderContainer.add(errorContainer,upload);
    }

    public void setUploaderErrorEvents(){
        upload.addFileRejectedListener(e -> {
            errorContainer.add(new Span("Falscher Dateityp"));
            isImageUploaded = false;
        });

        upload.addFailedListener(e -> {
            errorContainer.add(new Span("Hochladen der Datei fehlgeschlagen"));
            isImageUploaded = false;
        });
    }

    /**
     * This method sets the events for the news upload button. Once the button is clicked the content saved in the
     * buffer is then saved into the inputStream variable, imageEntity is created out of the uploaded file,
     * imageCreatingManager is created and given to the inputStream, imageEntity and the imageService and then the
     * imageCreationManager saves the file on the server and the database
     */
    public void setUploaderEventsForNews(){
        setUploadLabels();
        upload.addSucceededListener(e -> {
            inputStream = buffer.getInputStream();
            createImageEntity(changeGlobalFileName(e.getFileName()));
            imageCreationManager = new ImageCreationManager(inputStream, imageEntity, imageService);
            imageCreationManager.setMimeType(e.getMIMEType());
            imageCreationManager.save();
            isImageUploaded = true;
        });
        setUploaderErrorEvents();
    }

    public void setUploadLabels(){
        upload.setAcceptedFileTypes("image/jpeg", "image/png");

        NativeButton uploadButton = new NativeButton("Bild hochladen");
        upload.setUploadButton(uploadButton);

        Span label = new Span("Ziehen Sie die Datei per Drag & Drop hierher!");
        upload.setDropLabel(label);
        upload.setVisible(isOneImage);
    }

    /**
     * See description of setUploaderEventsForNews()
     */
    public void setUploaderEvents(){
        setUploadLabels();
        upload.addSucceededListener(e -> {
            inputStream = buffer.getInputStream();
            createImageEntity(changeGlobalFileName(e.getFileName()));

            imageCreationManager = new ImageCreationManager(inputStream, imageEntity, imageService);
            imageCreationManager.setMimeType(e.getMIMEType());
            imageCreationManager.save();
            isImageUploaded = true;
            UI.getCurrent().getPage().reload();

        });
        setUploaderErrorEvents();
    }

    public void setDeleteButtonEvent(){
        image.getDeleteButton().addClickListener(e -> {
            imageDeletionManager.delete();
            UI.getCurrent().getPage().reload();
            upload.setVisible(!isOneImage);
        });
    }

    public void createImageEntity(String fileName){
        imageEntity = new ImageEntity(fileName,createPath(fileName),
                pageEntity ,userEntity);
    }


    public String changeGlobalFileName(String imageFileName){
        return new Date().getTime() + "-" + imageFileName;
    }

    public void setAllImageEntitiesData(PageEntity pageEntity,
                                        UserEntity userEntity){
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }

    public void setOneImage(boolean isOneImage){
        this.isOneImage = isOneImage;
    }

    public String createPath(String fileName){
        return RESOURCES_DIR + fileName;
    }

    public void setPageEntity(PageEntity pageEntity){
        this.pageEntity = pageEntity;
    }

    public void setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public Div getUploaderContainer(){
        return uploaderContainer;
    }

    public ImageEntity getImageEntity(){
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity){
        this.imageEntity = imageEntity;
        this.imageDeletionManager = new ImageDeletionManager(imageEntity, imageService);
    }

    public int getIdOfNewsImage(){
        return imageEntity.getImageId();
    }

    public boolean isImageUploaded(){
        return isImageUploaded;
    }
}
