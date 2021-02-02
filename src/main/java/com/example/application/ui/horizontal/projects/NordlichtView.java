package com.example.application.ui.horizontal.projects;

import com.example.application.backend.entities.*;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.projects.ProjectService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.utils.images.ImagesManager;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Nordlicht View shows the project "Nordlicht"
 *
 *  @author Monika Martius and Laura Neuendorf
 *  @version 3.0
 *  @since 24.01.2020
 *  @lastUpdated 01.02.2021 from Jessica Reistel, Monika Martius
 */
@Route(value = "nordlicht", layout = MainView.class)
@PageTitle("Nordlicht")
public class NordlichtView extends Div {
    private ImagesManager imagesManager;
    private ImagesManager imagesManagerTwo;
    private final PageEntity pageEntity;
    private final ImageService imageService;
    private final UserEntity userEntity;

    private ProjectEntity projectEntity;

    private LinkEntity linkEntity;
    private LinkEntity linkEntityTwo;

    private int role;

    private Component leftComponent;

    public NordlichtView(PageService pageService, UserService userService, ImageService imageService,
                         ProjectService projectService, LinkService linkService) {
        setId("nordlicht");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        pageEntity = pageService.findPageById(12);
        this.imageService = imageService;

        projectEntity = projectService.findById(1);
        int linkOne = projectEntity.getLinkOne();
        int linkTwo = projectEntity.getLinkTwo();
        linkEntity = linkService.findById(linkOne);
        linkEntityTwo = linkService.findById(linkTwo);


        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        role = roleEntity.getRoleId();

        setContent();

        initializeImagesManager();
        initializeImagesManagerTwo();
        if(role == 1){
            initializeUploadContainer();
        }
        initializeRightContainer();
        initializeSplitLayout();
    }

    /**
     * This method fetches the data from the database
     * and displays it on the corresponding page
     */
    private void setContent(){
        H1 pageTitle = new H1(pageEntity.getTitle());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Projekte"), new Breadcrumb(pageEntity.getTitle()));

        Div box = new Div();
        box.add(breadcrumbs, pageTitle);
        box.setId("layoutBox");
        H2 pageText = new H2(pageEntity.getContent());
        this.add(box, pageText);
    }

    /**
     * This method initializes two div boxes with the content from the database of two sample projects
     */
    private void initializeRightContainer() {
        HorizontalLayout contentTextLayout = new HorizontalLayout();
        Div boxOne = new Div();
        Anchor linkOne = new Anchor(linkEntity.getUrl(), linkEntity.getTitle());
        linkOne.setTarget(linkEntity.getUrl());
        H3 hOne = new H3(projectEntity.getTitelTextBoxOne());
        Paragraph textOne = new Paragraph();
        textOne.add(new Paragraph(projectEntity.getTextBoxOne()));
        boxOne.add(hOne,textOne,linkOne);
        boxOne.setSizeFull();

        Div boxTwo = new Div();
        Anchor linkTwo = new Anchor(linkEntityTwo.getUrl(), linkEntityTwo.getTitle());
        linkOne.setTarget(linkEntityTwo.getUrl());
        H3 hTwo = new H3(projectEntity.getTitelTextBoxTwo());
        Paragraph textTwo = new Paragraph();
        textTwo.add(new Paragraph(projectEntity.getTextBoxTwo()));
        boxTwo.add(hTwo,textTwo,linkTwo);
        boxTwo.setSizeFull();
        contentTextLayout.add(boxOne, boxTwo);

        leftComponent = new VerticalLayout(contentTextLayout);
    }

    /**
     * This methods initializes the entity lists + containers
     */
    private  void initializeImagesManager(){
        imagesManager = new ImagesManager(pageEntity.getImages(), imageService, role);
        imagesManager.setImagesEntities(pageEntity.getImages());
        imagesManager.setAllImageEntitiesData(pageEntity,userEntity);
        imagesManager.setOneImage(true);
        imagesManager.initializeAllImages();
    }

    private  void initializeImagesManagerTwo(){
        imagesManagerTwo = new ImagesManager(pageEntity.getImages(), imageService, role);
        imagesManagerTwo.setImagesEntities(pageEntity.getImages());
        imagesManagerTwo.setAllImageEntitiesData(pageEntity,userEntity);
        imagesManagerTwo.setOneImage(true);
        imagesManagerTwo.initializeAllImages();
    }

    private void initializeSplitLayout() {
        VerticalLayout layout = new VerticalLayout(leftComponent);
        HorizontalLayout bigContainer = new HorizontalLayout();
        bigContainer.setId("layoutBigContainerPicture");
        Div imagesContainer = new Div();
        Div imagesContainerTwo = new Div();
        for(Image images : imagesManager.getImages()) imagesContainer.add(images);
        for(Image images : imagesManagerTwo.getImages()) imagesContainerTwo.add(images);
        bigContainer.add(imagesManager,imagesManagerTwo);
        bigContainer.add(imagesContainer,imagesContainerTwo);
        this.add(bigContainer,layout);
    }

    private void initializeUploadContainer(){
        imagesManager.initializeUploadContainer();
        Div imagesUploader = imagesManager.getImagesUploader();
        this.add(imagesUploader);

        imagesManagerTwo.initializeUploadContainer();
        Div imagesUploaderTwo = imagesManagerTwo.getImagesUploader();
        this.add(imagesUploaderTwo);
    }
}
