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
 *  @version 4.0
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

    private Button toAdd;
    private Button toDelete;

    private SplitLayout splitLayout;

    private Grid<GridJobOffer> jobOfferGrid;
    private Div pdfsUploader;
    private ComboBox<String> typeDropBox;
    private ComboBox<String> categoryDropBox;

    private NotificationService notificationService;

    TextField description;
    TextField location;
    Div errorContainer;
    Div addClearDiv;

    private boolean isDescriptionFilled = false;
    private boolean isTypeFilled = false;
    private boolean isCategoryFilled = false;
    private boolean isLocationFilled = false;

    private PdfsManager pdfsManager;
    private UserService userService;
    private UserEntity userEntity;
    private DocumentService documentService;
    private JobOfferService jobOfferService;
    private JobOfferEntity jobOfferEntity;


    public CareerView(PageService pageService, DocumentService documentService,
                      JobOfferService jobOfferService, UserService userService, NotificationService notificationService) {
        this.pageService = pageService;
        this.jobOfferService = jobOfferService;
        this.documentService = documentService;
        this.userService = userService;
        this.notificationService = notificationService;

        setId("career");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Unser Unternehmen"), new Breadcrumb(pageEntity.getTitle()));


        add(breadcrumbs, pageTitle, pageText);

        initializePdfsManager();
        initializeUploadContainer();
        initializeGrid();

        initializeLeftContainer();
        initializeRightContainer();

        initializeSplitLayout();
    }

    public void setData(){
        pageEntity = pageService.findPageById(8);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Paragraph(pageEntity.getContent());
        jobOfferEntity = new JobOfferEntity();

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);

        errorContainer = new Div();
        errorContainer.setText("Bitte laden Sie erst eine PDF Datei hoch und füllen Sie anschließend die nachfologenden Felder aus.");
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

    public void initializeGrid(){
        jobOfferGrid = new Grid<>();

        jobOfferGrid.setItems(GridJobOffer.JobOfferEntitiesConverter.convertJobOfferEntities(pageEntity.getJobOffers()));
        jobOfferGrid.setSelectionMode(Grid.SelectionMode.NONE);

        jobOfferGrid.addColumn(GridJobOffer::getTitle, "Bezeichnung").setHeader("Bezeichnung");
        jobOfferGrid.addColumn(GridJobOffer::getCategory, "Kategorie").setHeader("Kategorie");
        jobOfferGrid.addColumn(GridJobOffer::getType,"Stellenart").setHeader("Stellenart");
        jobOfferGrid.addColumn(GridJobOffer::getLocation, "Arbeitsort").setHeader("Arbeitsort");

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

    public void initializeSplitLayout(){
        splitLayout = new SplitLayout(leftComponent,rightComponent);
        splitLayout.setId("splitLayout");
        this.add(splitLayout);
    }

    public void initializePdfsManager(){
        List<DocumentEntity> tempDocumentsList = initializeJobOfferDocuments();
        pdfsManager = new PdfsManager(tempDocumentsList,notificationService,documentService);

        pdfsManager.setDocumentEntities(tempDocumentsList);
        pdfsManager.setAllDocumentEntitiesData("Verwaltung",pageEntity,pageEntity.getUser());
        pdfsManager.setOnePdf(false);

        pdfsManager.initializeAllPdfs();
    }

    public void initializeUploadContainer(){
        pdfsManager.initializeUploadContainerForJobOffers();
        pdfsUploader = pdfsManager.getPdfsUploader();
    }

    public List<DocumentEntity> initializeJobOfferDocuments(){
        List<DocumentEntity> tempDocumentsList = new ArrayList<>();
        for(JobOfferEntity jobOfferEntity : pageEntity.getJobOffers()){
            tempDocumentsList.add(jobOfferEntity.getDocument());
        }
        return tempDocumentsList;
    }

    public void initializeCategory(){
        categoryDropBox = new ComboBox<>();
        categoryDropBox.setItems("Fahrdienst", "Werkstatt", "Verwaltung");
        categoryDropBox.setPlaceholder("Kategorie");
        categoryDropBox.addValueChangeListener( event -> {
            if(event.getValue() == null){
                errorContainer.setText("Sie haben nichts ausgewählt");
                toAdd.setEnabled(false);
                isCategoryFilled = false;
            }else{
                jobOfferEntity.setCategory(event.getValue());
                isCategoryFilled = true;
            }
            toAdd.setEnabled(checkFilled());
        });
    }

    public void initializeType(){
        typeDropBox = new ComboBox<>();
        typeDropBox.setItems("Vollzeit", "Teilzeit", "Ausbildung", "Praktikum");
        typeDropBox.setPlaceholder("Stellenart");
        typeDropBox.addValueChangeListener( event -> {
            if(event.getValue() == null){
                errorContainer.setText("Sie haben nichts ausgewählt");
                toAdd.setEnabled(false);
                isTypeFilled = false;
            }else{
                jobOfferEntity.setType(event.getValue());
                isTypeFilled = true;
            }
            toAdd.setEnabled(checkFilled());
        });
    }

    public void initializeDescription(){
        description = new TextField();
        description.setPlaceholder("Bezeichnung");
        description.addValueChangeListener( event -> {
            if(event.getValue() == null){
                errorContainer.setText("Sie haben nichts ausgewählt");
                toAdd.setEnabled(false);
                isDescriptionFilled = false;
            }else{
                jobOfferEntity.setTitle(event.getValue());
                isDescriptionFilled = true;
            }
            toAdd.setEnabled(checkFilled());
        });
    }

    public void initializeLocation() {
        location = new TextField();
        location.setPlaceholder("Arbeitsort");
        location.addValueChangeListener( event -> {
            if(event.getValue() == null){
                errorContainer.setText("Sie haben nichts ausgewählt");
                toAdd.setEnabled(false);
                isLocationFilled = false;
            }else{
                jobOfferEntity.setLocation(event.getValue());
                isLocationFilled = true;
            }
            toAdd.setEnabled(checkFilled());
        });
    }

    public boolean checkFilled(){
        return isLocationFilled && isTypeFilled && isCategoryFilled && isDescriptionFilled && pdfsManager.isPdfUploaded();
    }

    public void readJobOffer(){
        jobOfferEntity.setUser(userEntity);
        jobOfferEntity.setPage(pageEntity);
        jobOfferEntity.setDocument(documentService.findDocumentById(pdfsManager.getDocumentOfJobOfferId()));
    }

    public void initializeAddAndClearButtons(){
        toAdd = new Button("Hinzufügen", event -> {
            readJobOffer();
            jobOfferService.save(jobOfferEntity);
            UI.getCurrent().getPage().reload();
        });
        toAdd.setEnabled(false);
        toAdd.setId("addButton");

        toDelete = new Button("Eingabe Löschen", event -> {
            description.clear();
            location.clear();
            typeDropBox.clear();
            categoryDropBox.clear();
            toAdd.setEnabled(false);
            jobOfferEntity = new JobOfferEntity();
        });

        addClearDiv = new Div();
        addClearDiv.add(toAdd,toDelete);
        addClearDiv.setId("addClearDiv");
    }

}