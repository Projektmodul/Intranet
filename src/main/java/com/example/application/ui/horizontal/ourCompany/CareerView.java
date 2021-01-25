package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.JobOfferEntity;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

/**
 *  Career View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "career", layout = MainView.class)
@PageTitle("Stellenangebote")
public class CareerView extends Div {

    private Component leftComponent;
    private Component rightComponent;

    private Upload uploadButton;
    private SplitLayout splitLayout;


    public CareerView() {
        setId("career");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");


        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    public void initializeLeftContainer(){
        H1 careerH = new H1("Stellenangebote");
        careerH.setId("careerHeadL");

        Label quoteCareer = new Label("Nutzen Sie die Chance und bewerben Sie sich online bei uns. ");
        quoteCareer.setClassName("quoteCareer");

        Div iconAndOfferDiv = new Div();
        iconAndOfferDiv.setClassName("iconDiv");
        Icon iconCareer = new Icon( VaadinIcon.USER);
        Label CareerOffer =new Label(" Stellenangebote");
        iconAndOfferDiv.add(iconCareer,CareerOffer);

        H4 latestOffer = new H4("Aktuelle Stellenangebote");
        latestOffer.setClassName("latestOffer");

        List<JobOfferEntity> jobOfferList = Arrays.asList(
                new JobOfferEntity(1,"Title 1","Bauingenieur/Elektroingenieur (m/w/d) ","Infrastruktur","vollzeit","Bremen"),
                new JobOfferEntity(1,"Title 2","Kraftomnibusfahrer (m/w/d)","Fahrdienst","vollzeit","Bremen"),
                new JobOfferEntity(1,"Title 3","Elektriker (m/w/d)","Infrastruktur","vollzeit","Bremen"));

        /*
         *Create a grid bound to the list
         */
        Grid<JobOfferEntity> grid = new Grid<>();
        grid.setItems(jobOfferList);
        grid.addColumn(JobOfferEntity::getDescription).setHeader("Bezeichnung");
        grid.addColumn(JobOfferEntity::getCategory).setHeader("Kategorie");
        grid.addColumn(JobOfferEntity::getType).setHeader("Stellenart");
        grid.addColumn(JobOfferEntity::getLocation).setHeader("Arbeitsort");
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS,GridVariant.LUMO_ROW_STRIPES,GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.getStyle().set("border", "2px solid #a6a6a6");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setId("tableCareer");

        Label pageContent = new Label("Zur Unterstützung unseres Teams suchen wir immer wieder ideenreiche und ehrgeizige Kolleginnen und Kollegen. Deshalb freuen wir uns auch über Initiativbewerbungen.");
        pageContent.setClassName("pageContent");

        HorizontalLayout menuLayout = new HorizontalLayout(careerH);
        menuLayout.setId("menuLayout");

        leftComponent = new VerticalLayout(menuLayout,quoteCareer,iconAndOfferDiv,grid, pageContent);
        leftComponent.setId("leftComponent");
    }

    public void initializeRightContainer(){
        initializeUploadButton();
        TextField description = new TextField();
        description.setLabel("Bezeichnung");
        TextField category = new TextField();
        category.setLabel("Kategorie");
        TextField type= new TextField();
        type.setLabel("Stellenart");
        TextField location = new TextField();
        location.setLabel("Arbeitsort");
        Button addButton = new Button("Hinzufügen");
        addButton.setId("addButton");
        Button clearButton = new Button("Eingabe Löschen", event -> {
            description.clear();
            category.clear();
            type.clear();
            location.clear();
        });
        Div addClearDiv = new Div();
        addClearDiv.add(addButton,clearButton);
        addClearDiv.setId("addClearDiv");


        rightComponent = new VerticalLayout(uploadButton,description,category,type,location,addClearDiv);
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

}