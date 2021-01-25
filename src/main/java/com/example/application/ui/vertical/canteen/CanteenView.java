package com.example.application.ui.vertical.canteen;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.pdfs.PDF;
import com.example.application.backend.utils.pdfs.PdfsManager;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Canteen View shows  a View for all menus.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 3.0
 * @since   14.12.2020
 * @lastUpdated 25.01.2021 by Laura Neuendorf, Monika Martius, Vanessa Skowronsky
 */

@Route(value = "canteen", layout = MainView.class)
@PageTitle("Betriebsrestaurant")
public class CanteenView extends Div {
    //private Component leftComponent;
    //private Component rightComponent;
    //private Upload uploadButton;
    //private SplitLayout splitLayout;
    //private Dialog imageDialog;

    private H1 pageTitle;
    private Image pdfImage;
    private Icon deleteIcon;
    private PageEntity pageEntity;
    private PageService pageService;
    private UserService userService;
    private DocumentService documentService;
    private NotificationService notificationService;
    private Paragraph pageContent;
    private Div bigContainer;
    private Div pdfsContainer;
    private Div pdfsUploader;
    private PdfsManager pdfsManager;


    public CanteenView(PageService pageService, UserService userService, NotificationService notificationService, DocumentService documentService) {
        setId("canteen");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.pageService = pageService;
        this.userService = userService;
        this.documentService = documentService;
        this.notificationService = notificationService;

        setData();

        initializePdfsManager();
        initializeBigContainer();
        initializeUploadContainer();

        pdfsManager.setOnePdf(true);
    }

    public void setData(){
        pageEntity = pageService.findPageById(26);
        pageTitle = new H1(pageEntity.getTitle());

        /*pageContent = new Span("Werfen Sie doch schon vorab einen Blick in unsere Speisekarte.");*/

        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        HorizontalLayout menuDeleteLayout = new HorizontalLayout(pageTitle);
        menuDeleteLayout.setId("menuDeleteLayout");

        this.add(pageTitle, pageContent, menuDeleteLayout);
    }

    public void initializePdfsManager() {
        pdfsManager = new PdfsManager(pageEntity.getDocuments(), notificationService, documentService);
        pdfsManager.setDocumentEntities(pageEntity.getDocuments());
        pdfsManager.setAllDocumentEntitiesData("Speiseplan", pageEntity, pageEntity.getUser());
        pdfsManager.initializeAllPdfs();
    }

    private void initializePDFs() {
        pdfsContainer = new Div();
        for (PDF pdf : pdfsManager.getPdfs()) pdfsContainer.add(pdf);
        bigContainer.add(pdfsContainer);
        bigContainer.add(pdfsManager);
    }

    public void initializeBigContainer() {
        bigContainer = new Div();
        initializePDFs();
        this.add(bigContainer);
    }

    public void initializeUploadContainer() {
        pdfsManager.initializeUploadContainer();
        pdfsUploader = pdfsManager.getPdfsUploader();
        this.add(bigContainer);
        this.add(pdfsUploader);
    }
}
