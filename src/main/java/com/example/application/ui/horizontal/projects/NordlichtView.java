package com.example.application.ui.horizontal.projects;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.ImagesManager;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Nordlicht View shows ...
 *
 *  @author Monika Martius
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated 24.01.2021 by Monika Martius
 */
@Route(value = "nordlicht", layout = MainView.class)
@PageTitle("Nordlicht")
public class NordlichtView extends Div {

    private ImagesManager imagesManager;
    private PageEntity pageEntity;
    private final ImageService imageService;
    private final UserEntity userEntity;
    private Div bigContainer;

    private Component leftComponent;
    private Component rightComponent;

    public NordlichtView(PageService pageService, UserService userService, ImageService imageService) {
        setId("nordlicht");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        this.imageService = imageService;

        pageEntity = pageService.findPageById(12);

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);

        initializeImagesManager();
        initializeBigContainer();
        initializeBigContainer();
        initializeUploadContainer();
        imagesManager.setOneImage(false);
        setData();
        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    private void setData(){
        H1 pageTitle = new H1(pageEntity.getTitle());
        Div box = new Div();
        box.add(pageTitle);
        box.setId("layoutBox");
        this.add(box);
    }

    private void initializeSplitLayout() {
        HorizontalLayout layout = new HorizontalLayout(leftComponent, rightComponent);
        this.add(layout);
    }

    private void initializeRightContainer() {
        HorizontalLayout contentTextLayout = new HorizontalLayout();
        Div boxOne = new Div();
        Anchor linkOne = new Anchor("https://www.bsag.de/unternehmen/ueber-uns/unsere-neue-strassenbahn.html", "-> Weiterlesen...");
        linkOne.setTarget("https://www.bsag.de/unternehmen/ueber-uns/unsere-neue-strassenbahn.html");
        H3 hOne = new H3("Bremens neue Straßenbahn ");
        Paragraph textOne = new Paragraph();
        textOne.add(new Paragraph("Das Nordlicht hat die Hansestadt erreicht. Bis Bremens neue Straßenbahngeneration in den Liniendienst geht, müssen noch zahlreiche Vorbereitungen getroffen werden. Wir begleiten das >>Nordlicht<< von seiner Ankunft bis zu seiner Indienststellung – schauen Sie mit uns hinter die Kulissen! "));
        boxOne.add(hOne,textOne,linkOne);
        boxOne.setSizeFull();

        Div boxTwo = new Div();
        Anchor linkTwo = new Anchor("https://www.bsag.de/de/projekte/mobil-fuer-morgen/elektromobilitaet.html", "-> Weiterlesen...");
        linkOne.setTarget("https://www.bsag.de/de/projekte/mobil-fuer-morgen/elektromobilitaet.html");
        H3 hTwo = new H3("E-Mobilität" );
        Paragraph textTwo = new Paragraph();
        textTwo.add(new Paragraph("Emissionsfreier Stadtverkehr ist noch eine Utopie, aber wäre das nicht wunderbar? Die gute Nachricht ist, es scheint in absehbarer Zeit möglich zu sein. Die Frage lautet allerdings, was ist der beste Weg zur Innenstadt mit sauberer Luft? Die BSAG trägt ihren Teil dazu bei: Sie ist der Nachhaltigkeit verpflichtet und fährt bereits heute, soweit sie elektrisch unterwegs ist, mit Strom aus erneuerbaren Quellen. Das ist zertifiziert, also durch unabhängige Begutachtung sichergestellt."));
        boxTwo.add(hTwo,textTwo,linkTwo);
        boxTwo.setSizeFull();
        contentTextLayout.add(boxOne,boxTwo);

        H2 pageText = new H2(pageEntity.getContent());
        rightComponent = new VerticalLayout(pageText,contentTextLayout);
    }

    private void initializeLeftContainer() {

        leftComponent = new VerticalLayout(bigContainer);
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
        initializeImages();
    }

    private void initializeUploadContainer(){
        imagesManager.initializeUploadContainer();
        Div imagesUploader = imagesManager.getImagesUploader();
        this.add(bigContainer);
        this.add(imagesUploader);
    }

}



