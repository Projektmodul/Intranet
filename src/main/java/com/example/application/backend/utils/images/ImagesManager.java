package com.example.application.backend.utils.images;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.ImageService;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.List;


/**
 * This class manages the images list and the imageManagers.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 4.0
 * @since   21.12.2020
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
public class ImagesManager extends Div {

    private ImageService imageService;

    private List<ImageEntity> imageEntities;
    private List<Image> images;

    private ImageManager imageManager;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private ImageEntity imageEntity;

    private int role;
    private boolean isOneImage;
    private Div imagesUploader;

    public ImagesManager(List<ImageEntity> imageEntities, ImageService imageService, int role){

        this.imageService = imageService;
        this.imageEntities = imageEntities;
        this.role = role;
        this.images = new ArrayList<>();
    }

    /**
     * This method creates an imageManager with a given imageService and a role, sets the entities of the imageManager,
     * creates an Image component (not Vaadin, see Image.java file) out of the ImageEntities, these images are then
     * added to a images container. Depending the size of the imageEntities-List the isOneImage will be set to true
     * or false
     */
    public void initializeAllImages(){
        imageManager = new ImageManager(imageService, role);
        imageManager.setAllImageEntitiesData(pageEntity,userEntity);
        for(ImageEntity imageEntity : imageEntities){
            imageManager.setImageEntity(imageEntity);
            imageManager.setOneImage(isOneImage);
            images.add(imageManager.createImage());
            imageManager.setDeleteButtonEvent();
        }
        if(imageEntities.size() > 1){
            imageManager.setOneImage(isOneImage);
        }else if(imageEntities.size() == 0){
            imageManager.setOneImage(true);
        }
        else{
            imageManager.setOneImage(isOneImage);
        }
    }

    public void initializeUploadContainer(){
        imagesUploader = new Div();
        imageManager.setUploaderEvents();
        imagesUploader.add(imageManager.getUploaderContainer());
    }

    public void setImagesEntities(List<ImageEntity> imageEntities){
        this.imageEntities = imageEntities;
    }

    public void setAllImageEntitiesData(PageEntity pageEntity,
                                        UserEntity userEntity){
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }

    public void initializeUploadContainerForNews(){
        imagesUploader = new Div();
        imageManager.setUploaderEventsForNews();
        imagesUploader.add(imageManager.getUploaderContainer());
    }

    public void setOneImage(boolean isOneImage){
        this.isOneImage = !isOneImage;
    }

    public void setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public void setPageEntity(PageEntity pageEntity){
        this.pageEntity = pageEntity;
    }

    public List<Image> getImages(){
        return images;
    }

    public void setImages(List<Image> images){
        this.images = images;
    }

    public Div getImagesUploader() {
        return imagesUploader;
    }

    public int getIdOfNewsImage(){
        return imageManager.getIdOfNewsImage();
    }

    public boolean isImageUploaded(){
        return imageManager.isImageUploaded();
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }
}
