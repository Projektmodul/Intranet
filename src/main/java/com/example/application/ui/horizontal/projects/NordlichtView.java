package com.example.application.ui.horizontal.projects;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.ProjectEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.projects.ProjectService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.utils.images.ImagesManager;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Nordlicht View shows ...
 *
 *  @author Monika Martius and Laura Neuendorf
 *  @version 2.0
 *  @since 24.01.2020
 *  @lastUpdated 26.01.2020
 */
@Route(value = "nordlicht", layout = MainView.class)
@PageTitle("Nordlicht")
public class NordlichtView extends Div {

    private ImagesManager imagesManager;
    private final PageEntity pageEntity;
    private final ImageService imageService;
    private final UserEntity userEntity;

    private ProjectEntity projectEntity;
    private ProjectService projectService;

    private LinkEntity linkEntity;
    private LinkEntity linkEntityTwo;
    private LinkService linkService;

    private Div bigContainer;

    private int linkOne;
    private int linkTwo;

    private Component leftComponent;
    private Component rightComponent;

    public NordlichtView(PageService pageService, UserService userService, ImageService imageService,
                         ProjectService projectService, LinkService linkService) {
        setId("nordlicht");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        pageEntity = pageService.findPageById(12);

        this.imageService = imageService;
        this.projectService = projectService;
        this.linkService = linkService;

        projectEntity = this.projectService.findById(1);
        linkOne = projectEntity.getLinkOne();
        linkTwo = projectEntity.getLinkTwo();
        linkEntity = this.linkService.findById(linkOne);
        linkEntityTwo = this.linkService.findById(linkTwo);


        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);

        setData();

        initializeImagesManager();
        initializeBigContainer();
        initializeUploadContainer();
        imagesManager.setOneImage(false);

        initializeRightContainer();
        initializeSplitLayout();
    }

    private void setData(){
        H1 pageTitle = new H1(pageEntity.getTitle());
        Div box = new Div();
        box.add(pageTitle);
        box.setId("layoutBox");
        H2 pageText = new H2(pageEntity.getContent());
        this.add(box, pageText);
    }

    private void initializeSplitLayout() {
        HorizontalLayout layout = new HorizontalLayout(leftComponent);
        this.add(layout);
    }

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

    private  void initializeImagesManager(){
        imagesManager = new ImagesManager(pageEntity.getImages(), imageService);
        imagesManager.setImagesEntities(pageEntity.getImages());
        imagesManager.setAllImageEntitiesData(pageEntity,userEntity);
        imagesManager.initializeAllImages();
    }

    private void initializeImages(){
        Div imagesContainer = new Div();
        for(Image image : imagesManager.getImages()) imagesContainer.add(image);
        bigContainer.add(imagesContainer);
        bigContainer.add(imagesManager);
    }

    private void initializeBigContainer(){
        bigContainer = new Div();
        bigContainer.setId("layoutBigContainerPicture");
        initializeImages();
    }

    private void initializeUploadContainer(){
        imagesManager.initializeUploadContainer();
        Div imagesUploader = imagesManager.getImagesUploader();
        this.add(bigContainer);
        this.add(imagesUploader);
    }
}
