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
 * @version 1.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class ImagesManager extends Div {



    ImageService imageService;

    private List<ImageEntity> imageEntities;
    private List<Image> images;

    private ImageManager imageManager;

    //Variables to create a new imageEntity, should be set by the View
    private PageEntity pageEntity;
    private UserEntity userEntity;


    public ImagesManager(List<ImageEntity> imageEntities, ImageService imageService){

        this.imageService = imageService;

        this.imageEntities = imageEntities;

        this.images = new ArrayList<>();

    }

    //will be called by the view after setting list
    public void initializeAllImages(){
        imageManager = new ImageManager(imageService);
        imageManager.setAllImageEntitiesData(pageEntity,userEntity);
        for(ImageEntity imageEntity : imageEntities){
            imageManager.setImageEntity(imageEntity);

            images.add(imageManager.createPDF());
            imageManager.setCancelButtonEvent();
            System.out.println("CREATED IMAGE");
            System.out.println("PATH: "+ imageEntity.getPath());
        }
        readList();

        imageManager.setUploaderEvents();
        this.add(imageManager.getUploaderContainer());
    }

    //will be set by the view :: gets the list of the page.
    public void setImagesEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    //Set by the view: global data for all documentEntities
    public void setAllImageEntitiesData(PageEntity pageEntity,
                                        UserEntity userEntity){
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }



    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void readList(){
        for(ImageEntity d : pageEntity.getImages()){
            System.out.println(d.toString());
        }
    }


}