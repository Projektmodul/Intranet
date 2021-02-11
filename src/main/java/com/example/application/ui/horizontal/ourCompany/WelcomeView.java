package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;

import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.roles.RoleService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.utils.images.ImagesManager;
import com.example.application.ui.MainView;

import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Welcome View shows a welcome Text and an Image
 *
 *  @author Sabrine Gamdou, Jessica Reistel, Laura Neuendorf
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 24.01.2021 by Anastasiya Jackwerth, Sabrine Gamdou
 */
@Route(value = "welcome", layout = MainView.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    private ImagesManager imagesManager;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private GetUserController getUserController;

    private PageService pageService;
    private UserService userService;
    private ImageService imageService;
    private RoleService roleService;

    private String username;
    private Div bigContainer;
    private Div imagesContainer;

    private H1 pageTitle;
    private Paragraph pageContent;
    private int role;

    private Div imagesUploader;

    public WelcomeView(PageService pageService, UserService userService, ImageService imageService, RoleService roleService) {
        setId("welcome");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        this.pageService = pageService;
        this.userService = userService;
        this.imageService = imageService;
        this.roleService = roleService;
        getUserController = new GetUserController();

        setData();

        username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        role = roleEntity.getRoleId();

        initializeImagesManager();
        initializeBigContainer();
        initializeUploadContainer();

        imagesManager.setOneImage(true);
    }

    private void setData(){
        pageEntity = pageService.findPageById(4);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph();
        pageContent.setId("pageContentWelcome");
        pageContent.setText(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Unser Unternehmen"), new Breadcrumb(pageEntity.getTitle()));

        this.add(breadcrumbs, pageTitle, pageContent);
    }

    private void initializeImagesManager(){
        imagesManager = new ImagesManager(pageEntity.getImages(), imageService, role);
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
        if(role == 1){
            this.add(imagesUploader);
        }
    }
}
