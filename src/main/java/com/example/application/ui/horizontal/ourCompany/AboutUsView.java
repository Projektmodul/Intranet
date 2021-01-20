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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



/**
 *  AboutUs View shows ...
 *
 *  @author Laura Neuendorf, Jessica Reistel
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 20.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou, Laura Neuendorf
 */

@Route(value = "aboutUs", layout = MainView.class)
@PageTitle("Über Uns")
public class AboutUsView extends Div {

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

    public AboutUsView(PageService pageService, UserService userService, ImageService imageService) {
        this.pageService = pageService;
        this.userService = userService;
        this.imageService = imageService;

        setId("aboutUs");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();

        userEntity = pageEntity.getUser();

        initializeImagesManager();
        initializeBigContainer();
    }

    private void setData(){
        pageEntity = pageService.findPageById(4);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle, pageContent);
    }

    private void initializeImagesManager(){
        imagesManager = new ImagesManager(pageEntity.getImages(), imageService);
        imagesManager.setImagesEntities(pageEntity.getImages());
        imagesManager.setAllImageEntitiesData(pageEntity, userEntity);

        imagesManager.initializeAllImages();
        this.add(imagesManager);
    }

    private void initializeImages(){
        imagesContainer = new Div();
        for(Image image : imagesManager.getImages()) imagesContainer.add(image);
        bigContainer.add(imagesContainer);
    }

    private void initializeBigContainer(){
        bigContainer = new Div();
        initializeImages();
        this.add(bigContainer);
    }

}