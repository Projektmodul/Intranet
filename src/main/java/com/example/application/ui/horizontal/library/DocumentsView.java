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
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Documents View shows a grid-view for all documents.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 7.0
 * @since   12.01.2021
 * @lastUpdated 07.02.2021 by Jessica Reistel
 */
@Route(value = "documents", layout = MainView.class)
@PageTitle("Unterlagen")
public class DocumentsView extends Div {

    private PdfsManager pdfsManager;
    private final PageEntity pageEntity;

    private final DocumentService documentService;
    private final NotificationService notificationService;

    private final int role;
    private String keyword;

    public DocumentsView(PageService pageService, UserService userService, DocumentService documentService,
                         NotificationService notificationService) {
        setId("documents");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        this.documentService = documentService;
        this.notificationService = notificationService;

        pageEntity = pageService.findPageById(14);
        H1 pageTitle = new H1(pageEntity.getTitle());
        pageTitle.setId("pageTitle");

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        role = roleEntity.getRoleId();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle);

        initializePdfsManager();
        if(role == 1){
            initializeRadioButtonsForKeyword();
            initializeUploadContainer();
        }
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

        keyword = "Allgemein";
        pdfsManager.getPdfManager().setKeyword(keyword);
        messageContainer.setText("Sie haben ausgewählt: Allgemein");
        messageContainer.setId("messageContainer");

        radioGroup.addValueChangeListener(event -> {
            if (event.getValue() != null){
                messageContainer.setText("Sie haben ausgewählt: " + event.getValue());
                keyword = event.getValue();
                pdfsManager.getPdfManager().setKeyword(keyword);
                pdfsManager.getPdfManager().setNotificationCategory("Pdf-Dokument");
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
        pdfsManager.getPdfManager().setNotificationCategory("Pdf-Dokument");
    }

    public void initializeUploadContainer(){
        pdfsManager.initializeUploadContainer();
        Div pdfsUploader = pdfsManager.getPdfsUploader();

        this.add(pdfsUploader);
    }

    public void initializeGrid(){
        Grid<GridDocument> documentsGrid = new Grid<>();

        documentsGrid.setItems(GridDocument.DocumentEntitiesConverter.convertDocumentEntities(
                pageEntity.getDocuments()
        ));

        documentsGrid.addColumn(GridDocument::getKeyword, "keyword").setHeader("Abteilung").setFooter("Gesamt: "+ pageEntity.getDocuments().size());
        documentsGrid.addColumn(new ComponentRenderer<>(GridDocument::getDownloadLink)).setHeader("Dateiname");
        documentsGrid.setId("gridFullPage");

        add(documentsGrid);
    }
}
