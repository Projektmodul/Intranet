package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.utils.images.ImagesManager;
import com.example.application.ui.MainView;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Welcome View shows ...
 *
 *  @author Sabrine Gamdou, Jessica Reistel, Laura Neuendorf
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */
@Route(value = "welcome", layout = MainView.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    private ImagesManager imagesManager;

    private PageEntity pageEntity;
    private UserEntity userEntity;

    private PageService pageService;
    private UserService userService;
    private ImageService imageService;

    private Div bigContainer;
    private Div imagesContainer;

    private H1 pageTitle;
    private Paragraph pageContent;

    private Div imagesUploader;

    public WelcomeView(PageService pageService, UserService userService, ImageService imageService) {
        this.pageService = pageService;
        this.userService = userService;
        this.imageService = imageService;

        setId("welcome");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();

        userEntity = pageEntity.getUser();

        initializeImagesManager();
        initializeBigContainer();
        initializeUploadContainer();
        imagesManager.setOneImage(true);

    }

    private void setData(){
        PageEntity pageEntity = pageService.findPageById(4);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph();
        pageContent.setId("pageContentWelcome");
        pageContent.setText(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle,pageContent);
    }

    private void initializeImagesManager(){
        imagesManager = new ImagesManager(pageEntity.getImages(), imageService);
        imagesManager.setImagesEntities(pageEntity.getImages());
        imagesManager.setAllImageEntitiesData(pageEntity, userEntity);

        imagesManager.initializeAllImages();
    }

    private void initializeImages(){
        imagesContainer = new Div();
        for(Image image : imagesManager.getImages()) imagesContainer.add(image);
        bigContainer.add(imagesContainer);
        bigContainer.add(imagesManager);
    }

    private void initializeBigContainer(){
        bigContainer = new Div();
        initializeImages();
    }

    private void initializeUploadContainer(){
        imagesManager.initializeUploadContainer();
        imagesUploader = imagesManager.getImagesUploader();
        this.add(bigContainer);
        this.add(imagesUploader);
    }

}
