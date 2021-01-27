package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.GridDocument;
import com.example.application.backend.utils.pdfs.PdfsManager;
import com.example.application.ui.MainView;
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
 * @author  Sabrine Gamdou, Anastasiya Jackwerth, Jessica Reistel
 * @version 5.0
 * @since   12.01.2021
 * @lastUpdated 27.01.2021 by Jessica Reistel
 */

@Route(value = "documents", layout = MainView.class)
@PageTitle("Unterlagen")
public class DocumentsView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private Paragraph pageText;
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
    private String keyword;

    public DocumentsView(PageService pageService, UserService userService, DocumentService documentService,
                         NotificationService notificationService) {
        this.pageService = pageService;
        this.userService = userService;
        this.documentService = documentService;
        this.notificationService = notificationService;

        setId("documents");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        pageEntity = pageService.findPageById(14);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Paragraph(pageEntity.getContent());






        setData();
        //userEntity = pageEntity.getUser();

        initializeRadioButtonsForKeyword();
        initializePdfsManager();
        initializeUploadContainer();
        initializeGrid();

        this.getStyle().set("width","100%");
    }

    public void setData(){
        pageEntity = pageService.findPageById(14);

        pageTitle = new H1(pageEntity.getTitle());
        pageTitle.setId("pageTitle");
        add(pageTitle);
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
        pdfsManager = new PdfsManager(pageEntity.getDocuments(),notificationService,documentService);

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

        documentsGrid.addColumn(GridDocument::getKeyword, "keyword").setHeader("Abteilung");
        documentsGrid.addColumn(new ComponentRenderer<>(GridDocument::getDownloadLink)).setHeader("Dateiname");
        documentsGrid.setId("gridFullPage");

        add(documentsGrid);
    }





}
