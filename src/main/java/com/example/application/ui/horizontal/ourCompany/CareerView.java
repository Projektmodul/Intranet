package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.*;
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
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

/**
 *  Career View shows ...
 *
 *  @author Monika Martius, Jessica Reistel, Litharshiga Sivarasa, Sabrine Gamdou, Anastasiya Jackwerth
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 07.02.2021 by Jessica Reistel

 */
@Route(value = "career", layout = MainView.class)
@PageTitle("Stellenangebote")
public class CareerView extends Div {

    private PageService pageService;
    private UserService userService;
    private JobOfferService jobOfferService;
    private NotificationService notificationService;
    private DocumentService documentService;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private JobOfferEntity jobOfferEntity;

    private PdfsManager pdfsManager;
    private Div pdfsUploader;

    private H1 pageTitle;
    private Paragraph pageContent;

    private Button toAdd;
    private Button buttonAdd;

    private Breadcrumbs breadcrumbs;
    private Dialog addDialog;

    private ComboBox<String> typeDropBox;
    private ComboBox<String> categoryDropBox;


    private TextField description;
    private TextField location;
    private Div errorContainer;
    private Div addClearDiv;

    private boolean isDescriptionFilled = false;
    private boolean isTypeFilled = false;
    private boolean isCategoryFilled = false;
    private boolean isLocationFilled = false;

    private int role;

    public CareerView(PageService pageService, DocumentService documentService, JobOfferService jobOfferService,
                      UserService userService, NotificationService notificationService) {
        this.pageService = pageService;
        this.jobOfferService = jobOfferService;
        this.documentService = documentService;
        this.userService = userService;
        this.notificationService = notificationService;

        setId("career");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();
        add(breadcrumbs, pageTitle, pageContent);
        if(role==1){
            add(buttonAdd);
        }

        initializePdfsManager();
        initializeUploadContainer();
        initializeGrid();

        initializeDialog();
    }

    public void setData(){
        pageEntity = pageService.findPageById(8);
        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());

        jobOfferEntity = new JobOfferEntity();

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);

        RoleEntity roleEntity = userEntity.getRole();
        role = roleEntity.getRoleId();

        errorContainer = new Div();
        errorContainer.setText("Bitte laden Sie erst eine PDF Datei hoch und füllen Sie anschließend die nachfolgenden" +
                " Felder aus.");

        breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Unser Unternehmen"), new Breadcrumb(pageEntity
                .getTitle()));

        buttonAdd = new Button("Stellenangebot hinzufügen", new Icon(VaadinIcon.USER));
        buttonAdd.addClickListener(e -> initializeDialog().open());
        buttonAdd.setIconAfterText(true);
    }

    public void initializeGrid(){
        Grid<GridJobOffer> jobOfferGrid = new Grid<>();

        jobOfferGrid.setItems(GridJobOffer.JobOfferEntitiesConverter.convertJobOfferEntities(pageEntity.getJobOffers()));
        jobOfferGrid.setSelectionMode(Grid.SelectionMode.NONE);

        jobOfferGrid.addColumn(GridJobOffer::getTitle, "Bezeichnung").setHeader("Bezeichnung")
                .setFooter("Gesamt: "+ GridJobOffer.JobOfferEntitiesConverter.convertJobOfferEntities(pageEntity
                        .getJobOffers()).size());
        jobOfferGrid.addColumn(GridJobOffer::getCategory, "Kategorie").setHeader("Kategorie");
        jobOfferGrid.addColumn(GridJobOffer::getType,"Stellenart").setHeader("Stellenart");
        jobOfferGrid.addColumn(GridJobOffer::getLocation, "Arbeitsort").setHeader("Arbeitsort");

        jobOfferGrid.addColumn(new ComponentRenderer<>(GridJobOffer::getDownloadLink)).setHeader("Dateiname");

        jobOfferGrid.setSelectionMode(Grid.SelectionMode.NONE);
        jobOfferGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        jobOfferGrid.setId("gridFullPage");

        if(role!=1) {
            jobOfferGrid.getStyle().set("margin-top", "20px");
        }

        this.add(jobOfferGrid);
    }

    public Dialog initializeDialog(){
        initializeCategory();
        initializeType();
        initializeDescription();
        initializeLocation();

        initializeAddAndClearButtons();

        HorizontalLayout descriptionLocation = new HorizontalLayout(description, location);
        HorizontalLayout categoryType = new HorizontalLayout(categoryDropBox, typeDropBox);

        Component dialogComponent = new VerticalLayout(errorContainer, pdfsUploader, descriptionLocation, categoryType,
                addClearDiv);

        addDialog = new Dialog();
        addDialog.setCloseOnOutsideClick(false);
        addDialog.setCloseOnEsc(false);

        addDialog.add(new H2("Stellenangebot hinzufügen"), dialogComponent);

        return addDialog;
    }

    public void initializePdfsManager(){
        List<DocumentEntity> tempDocumentsList = initializeJobOfferDocuments();
        pdfsManager = new PdfsManager(tempDocumentsList,notificationService,documentService, role);

        pdfsManager.setDocumentEntities(tempDocumentsList);
        pdfsManager.setAllDocumentEntitiesData("Verwaltung",pageEntity,pageEntity.getUser());
        pdfsManager.setOnePdf(false);

        pdfsManager.initializeAllPdfs();
    }

    public void initializeUploadContainer(){
        pdfsManager.initializeUploadContainerForJobOffers();
        pdfsUploader = pdfsManager.getPdfsUploader();
        pdfsUploader.setId("careerUploader");
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

        Button toDelete = new Button("Eingabe Löschen", event -> {
            description.clear();
            location.clear();
            typeDropBox.clear();
            categoryDropBox.clear();
            toAdd.setEnabled(false);
            jobOfferEntity = new JobOfferEntity();
        });

        Button cancel = new Button("Abbrechen", e -> addDialog.close());

        addClearDiv = new Div();
        addClearDiv.add(toAdd,toDelete,cancel);
        addClearDiv.setId("addClearDiv");
    }

}