package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.JobOfferEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.files.JobOfferService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.GridJobOffer;
import com.example.application.backend.utils.pdfs.PdfsManager;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

/**
 *  Career View shows ...
 *
 *  @author Monika Martius, Jessica Reistel
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 01.02.2021 by Sabrine Gamdou, Anastasiya Jackwerth
 */
@Route(value = "career", layout = MainView.class)
@PageTitle("Stellenangebote")
public class CareerView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private Paragraph pageText;
    private PageEntity pageEntity;
    private Component leftComponent;
    private Component rightComponent;
    private Upload uploadButton;
    private Button toAdd;
    private Button toDelete;
    private SplitLayout splitLayout;

    private Grid<GridJobOffer> jobOfferGrid;
    private DocumentService documentService;
    private NotificationService notificationService;

    private Div pdfsUploader;
    private String keyword;

    private PdfsManager pdfsManager;
    private UserService userService;
    private UserEntity userEntity;
    private JobOfferService jobOfferService;
    private JobOfferEntity jobOfferEntity;


    public CareerView(PageService pageService, DocumentService documentService,
                      JobOfferService jobOfferService) {
        this.pageService = pageService;
        this.jobOfferService = jobOfferService;
        this.documentService = documentService;

        setId("career");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        int maxId = jobOfferService.findMaxId();
        pageEntity = pageService.findPageById(8);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Paragraph(pageEntity.getContent());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Unser Unternehmen"), new Breadcrumb(pageEntity.getTitle()));

        DocumentEntity documentManagement = new DocumentEntity();
        documentManagement.setFileName("Verwaltung-Dokument");
        documentManagement.setKeyword("Verwaltung");

        add(breadcrumbs, pageTitle, pageText);

        initializeTreeGrid();
        initializePdfsManager();
        initializeUploadContainer();
        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    public void initializeLeftContainer(){
        Label quoteCareer = new Label("Nutzen Sie die Chance und bewerben Sie sich online bei uns. ");
        quoteCareer.setClassName("quoteCareer");

        Div iconAndOfferDiv = new Div();
        iconAndOfferDiv.setClassName("iconDiv");
        Icon iconCareer = new Icon( VaadinIcon.USER);
        Label CareerOffer =new Label(" Stellenangebote");
        iconAndOfferDiv.add(iconCareer,CareerOffer);


        Label pageContent = new Label("Zur Unterstützung unseres Teams suchen wir immer wieder ideenreiche und ehrgeizige Kolleginnen und Kollegen. Deshalb freuen wir uns auch über Initiativbewerbungen.");
        pageContent.setClassName("pageContent");

        leftComponent = new VerticalLayout(quoteCareer,iconAndOfferDiv,jobOfferGrid, pageContent);
        leftComponent.setId("leftComponent");
    }

    public void initializeTreeGrid(){
        jobOfferGrid = new Grid<>();
        jobOfferGrid.setItems(GridJobOffer.JobOfferEntitiesConverter.convertJobOfferEntities(pageEntity.getJobOffers()));
        jobOfferGrid.setSelectionMode(Grid.SelectionMode.NONE);
        jobOfferGrid.addColumn(GridJobOffer::getTitle, "Bezeichnung").setHeader("Bezeichnung");
        jobOfferGrid.addColumn(GridJobOffer::getCategory, "Kategorie").setHeader("Kategorie");
        jobOfferGrid.addColumn(GridJobOffer::getType,"Stellenart").setHeader("Stellenart");
        jobOfferGrid.addColumn(GridJobOffer::getLocation, "Arbeitsort").setHeader("Arbeitsort");
        jobOfferGrid.addColumn(GridJobOffer::getKeyword, "keyword").setHeader("Abteilung");
        jobOfferGrid.addColumn(new ComponentRenderer<>(GridJobOffer::getDownloadLink)).setHeader("Dateiname");
        jobOfferGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS,GridVariant.LUMO_ROW_STRIPES,GridVariant.LUMO_WRAP_CELL_CONTENT);
        jobOfferGrid.getStyle().set("border", "2px solid #a6a6a6");
        jobOfferGrid.setSelectionMode(Grid.SelectionMode.NONE);
        jobOfferGrid.setId("tableCareer");
    }

    public void initializeRightContainer(){
        initializeCategory();
        initializeType();
        initializeDescription();
        initializeLocation();

        initializeAddAndClearButtons();

        rightComponent = new VerticalLayout(errorContainer, pdfsUploader,description,categoryDropBox,typeDropBox,location, addClearDiv);
        rightComponent.setId("rightComponent");
    }

    public void initializeUploadButton(){
        MemoryBuffer buffer = new MemoryBuffer();
        uploadButton = new Upload(buffer);

        uploadButton.setUploadButton(new Button("Stellenangebot hochladen"));
        uploadButton.setDropLabel(new Span("PDF-Dokument hier reinziehen"));

    }



    public void initializeSplitLayout(){
        splitLayout = new SplitLayout(leftComponent,rightComponent);
        splitLayout.setId("splitLayout");
        this.add(splitLayout);
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

}