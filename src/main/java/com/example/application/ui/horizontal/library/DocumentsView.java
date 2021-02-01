package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.GridDocument;
import com.example.application.backend.utils.pdfs.PdfsManager;

import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Documents View shows a grid-view for all documents.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 6.0
 * @since   12.01.2021
 * @lastUpdated 01.02.2021 by Laura Neuendorf
 */

@Route(value = "documents", layout = MainView.class)
@PageTitle("Unterlagen")
public class DocumentsView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private PageEntity pageEntity;

    //private TreeGrid<DocumentEntity> documentsGrid;
    private VerticalLayout pageContentLayout;

    private PdfsManager pdfsManager;
    private UserEntity userEntity;
    private UserService userService;
    private DocumentService documentService;
    private NotificationService notificationService;

    private Grid<GridDocument> documentsGrid;

    private Div pdfsUploader;
    private int role;
    private String keyword;
    private Breadcrumbs breadcrumbs;

    public DocumentsView(PageService pageService, UserService userService, DocumentService documentService,
                         NotificationService notificationService) {
        setId("documents");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        this.pageService = pageService;
        this.userService = userService;
        this.documentService = documentService;
        this.notificationService = notificationService;


        pageEntity = pageService.findPageById(14);
        pageTitle = new H1(pageEntity.getTitle());
        pageTitle.setId("pageTitle");

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        role = roleEntity.getRoleId();

        breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle);

        //userEntity = pageEntity.getUser();
        if(role == 1) {
            initializeRadioButtonsForKeyword();
            initializeUploadContainer();
        }
        initializePdfsManager();
        initializeGrid();

        this.getStyle().set("width","100%");
    }


    public void initializeRadioButtonsForKeyword(){
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();

        H4 chooseDepartment = new H4("Bitte wählen Sie eine Abteilung aus: ");
        radioGroup.setItems("Allgemein", "Fahrdienst", "Verwaltung", "Werkstatt");
        radioGroup.setValue("Allgemein");
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        Div messageContainer = new Div();
        //messageContainer.setText("Bitte wählen Sie eine Abteilung aus, bevor Sie ein neues Dokument hinzufügen.");

        keyword = "Allgemein";
        messageContainer.setText("Sie haben ausgewählt: Allgemein");
        messageContainer.setId("messageContainer");

        radioGroup.addValueChangeListener(event -> {
            if (event.getValue() != null){
                messageContainer.setText("Sie haben ausgewählt: " + event.getValue());
                keyword = event.getValue();
                pdfsManager.getPdfManager().setKeyword(keyword);
                System.out.println("Keyword: " + event.getValue());
            }
        });

        add(chooseDepartment ,radioGroup, messageContainer);
    }

    public void initializePdfsManager(){
        pdfsManager = new PdfsManager(pageEntity.getDocuments(),notificationService,documentService, role);

        pdfsManager.setDocumentEntities(pageEntity.getDocuments());
        pdfsManager.setAllDocumentEntitiesData(keyword,pageEntity,pageEntity.getUser());
        pdfsManager.setOnePdf(false);

        pdfsManager.initializeAllPdfs();
    }

    public void initializeUploadContainer(){
        pdfsManager.initializeUploadContainer();
        pdfsUploader = pdfsManager.getPdfsUploader();

        this.add(pdfsUploader);
    }

    public void initializeGrid(){
        documentsGrid = new Grid<>();

        documentsGrid.setItems(GridDocument.DocumentEntitiesConverter.convertDocumentEntities(
                pageEntity.getDocuments()
        ));

        documentsGrid.addColumn(GridDocument::getKeyword, "keyword").setHeader("Abteilung").setFooter("Gesamt: "+ pageEntity.getDocuments().size() + " Dokumente");
        documentsGrid.addColumn(new ComponentRenderer<>(GridDocument::getDownloadLink)).setHeader("Dateiname");
        documentsGrid.setId("gridFullPage");

        add(documentsGrid);
    }

}
