package com.example.application.backend.utils.images;

import com.example.application.backend.entities.*;
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
 * @version 5.0
 * @since   19.01.2021
 * @lastUpdated 01.02.2021 from Jessica Reistel, Monika Martius
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

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private int role;

    private final String RESOURCES_DIR = "~/uploads";

    public ImageManager(ImageService imageService, int role){
        this.imageService = imageService;
        this.role = role;
        isOneImage = true;
        initializeUploader();
    }
    public Image createPDF(){
        image = new Image(imageEntity, inputStream, role);
        return image;
    }

    private void initializeUploader(){
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);
        uploaderContainer = new Div();

        errorContainer = new Div();
        uploaderContainer.add(errorContainer,upload);

    }

    public void setUploaderEvents(){
        upload.setAcceptedFileTypes("image/jpeg", "image/png");

        NativeButton uploadButton = new NativeButton("Bild hochladen");
        upload.setUploadButton(uploadButton);

        Span label = new Span("Ziehen Sie die Datei per Drag & Drop hierher!");
        upload.setDropLabel(label);

        upload.setVisible(isOneImage);
        System.out.println("isOneImage: " + isOneImage);
        upload.addSucceededListener(e -> {
            inputStream = buffer.getInputStream();
            createImageEntity(changeGlobalFileName(e.getFileName()));
            //pdf.setInputStream(inputStream);
            imageCreationManager = new ImageCreationManager(inputStream, imageEntity, imageService);
            imageCreationManager.setMimeType(e.getMIMEType());
            imageCreationManager.save();
            UI.getCurrent().getPage().reload();

        });

        upload.addFileRejectedListener(e -> errorContainer.add(new Span(e.getErrorMessage())));

        upload.addFailedListener(e -> errorContainer.add(new Span("Hochladen der Datei fehlgeschlagen")));

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

    //Data set by the ImagesManager
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

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public Div getUploaderContainer() {
        return uploaderContainer;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
        this.imageDeletionManager = new ImageDeletionManager(imageEntity, imageService);
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
