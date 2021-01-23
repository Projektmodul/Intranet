package com.example.application.ui.vertical.canteen;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.pdfs.PDF;
import com.example.application.backend.utils.pdfs.PdfsManager;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Canteen View shows  a View for all menus.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 2.0
 * @since   14.12.2020
 * @lastUpdated 23.01.2021 from Sabrine Gamdou, Anastasiya Jackwerth
 */

@Route(value = "canteen", layout = MainView.class)
@PageTitle("Betriebsrestaurant")
public class CanteenView extends Div {

    private H1 pageTitle;
    private Paragraph pageContent;

    private PdfsManager pdfsManager;

    private PageEntity pageEntity;
    private UserEntity userEntity;

    private PageService pageService;
    private UserService userService;
    private DocumentService documentService;
    private NotificationService notificationService;

    private Div bigContainer;
    private Div pdfsContainer;
    private Div pdfsUploader;


    public CanteenView(PageService pageService, UserService userService, DocumentService documentService,
                       NotificationService notificationService) {
        this.pageService = pageService;
        this.userService = userService;
        this.documentService = documentService;
        this.notificationService = notificationService;

        setId("canteen");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        setData();
        userEntity = pageEntity.getUser();


        initializePdfsManager();
        initializeBigContainer();
        initializeUploadContainer();

        pdfsManager.setOnePdf(true);

    }

    public void setData(){
       pageEntity = pageService.findPageById(26);

        pageTitle = new H1(pageEntity.getTitle());
        pageTitle.setId("pageTitle");

        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        HorizontalLayout menuDeleteLayout = new HorizontalLayout(pageTitle);
        menuDeleteLayout.setId("menuDeleteLayout");

        this.add(pageTitle, pageContent, menuDeleteLayout);
    }

    public void initializePdfsManager(){
        pdfsManager = new PdfsManager(pageEntity.getDocuments(),notificationService,documentService);
        pdfsManager.setDocumentEntities(pageEntity.getDocuments());
        pdfsManager.setAllDocumentEntitiesData("Speiseplan",pageEntity,pageEntity.getUser());

        pdfsManager.initializeAllPdfs();

    }

    private void initializePDFs(){
        pdfsContainer = new Div();
        for(PDF pdf : pdfsManager.getPdfs()) pdfsContainer.add(pdf);
        bigContainer.add(pdfsContainer);
        bigContainer.add(pdfsManager);
    }

    public void initializeBigContainer(){
        bigContainer = new Div();
        initializePDFs();
        this.add(bigContainer);
    }

    public void initializeUploadContainer(){
        pdfsManager.initializeUploadContainer();
        pdfsUploader = pdfsManager.getPdfsUploader();
        this.add(bigContainer);
        this.add(pdfsUploader);
    }
}
