

package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.NoticeBoardOfferEntity;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

/**
 *  @author Monika Martius
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated 19.01.2021
 */
@Route(value = "noticeBoard", layout = MainView.class)
@PageTitle("Schwarzes Brett")

public class NoticeBoardView extends Div {


    //private noticeBoardService noticeBoardService;

    private H1 pageTitle;
    private Icon deleteIcon;
    private Icon editIcon;
    private Span pageContent;
    private Upload uploadButton;
    private HorizontalLayout layoutSplit;
    private RadioButtonGroup radioOffer;
    private Button toAdd;

    private Component leftComponent;
    private Component rightComponent;
    //private CardListNoticeBoard card;

    public NoticeBoardView() {
        setId("noticeBoard");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    public void initializeLeftContainer() {

        pageTitle = new H1("Schwarzes Brett");
        pageTitle.setId("pageTitle");

        Div box = new Div();
        box.add(pageTitle);
        box.setId("layoutBox");

        deleteIcon = new Icon(VaadinIcon.TRASH);
        deleteIcon.setId("deleteIcon");

        editIcon = new Icon(VaadinIcon.EDIT);
        editIcon.setId("editIcon");

        HorizontalLayout editDeleteIcon = new HorizontalLayout();
        editDeleteIcon.add(box, editIcon, deleteIcon);
        editDeleteIcon.setAlignItems(FlexComponent.Alignment.END);
        editDeleteIcon.setClassName("editDeleteIcon");
        pageContent = new Span("Wir wünschen Ihnen viel Spaß beim Suchen, Finden, Anbieten und Stöbern!");

        List<NoticeBoardOfferEntity> objectList = Arrays.asList(
        new NoticeBoardOfferEntity(100, "Wohnung", "Biete", "liliad@gmail.com"),
        new NoticeBoardOfferEntity(101, "Wohnung", "Suche", "miamaia@gmail.com"),
        new NoticeBoardOfferEntity(102, "Katze", "Suche", "ldfsdfdfd@gmail.com"),
        new NoticeBoardOfferEntity(103, "Auto", "Biete", "nnfdgfg@gmail.com"),
        new NoticeBoardOfferEntity(104, "Wohnung",  "Suche", "liliad@gmail.com"),
        new NoticeBoardOfferEntity(105, "Wohnung",  "Suche", "miamaia@gmail.com"));

        Grid<NoticeBoardOfferEntity> grid = new Grid<>();
        grid.setItems(objectList);

        //grid.removeColumnByKey("noticeBoardOfferId");
        grid.addColumn(NoticeBoardOfferEntity::getTitle).setHeader("Kategorie");
        grid.addColumn(NoticeBoardOfferEntity::getDescription).setHeader("Suche/Biete");
        grid.addColumn(NoticeBoardOfferEntity::getCategory).setHeader("E-Mail");
        grid.setId("tableNoticeBoard");

        leftComponent = new VerticalLayout(editDeleteIcon,pageContent,grid);
        leftComponent.setId("leftLayout");
    }

    public void initializeRightContainer(){
        initializeUploadButton();
        initializeRadioButton();
        TextArea textArea = new TextArea("Beschreibung");
        textArea.setId("styleTextArea");
        textArea.setPlaceholder("Anzeige aufgeben ...");
        toAdd = new Button();
        toAdd.setText("Hinzufügen");
        toAdd.setId("layoutSetID");
        rightComponent = new VerticalLayout(textArea,uploadButton,radioOffer,toAdd);
        rightComponent.setId("rightLayout");
    }

    public void initializeSplitLayout(){
        layoutSplit = new HorizontalLayout(leftComponent,rightComponent);
        layoutSplit.setId("splitLayout");
        this.add(layoutSplit);
    }

    public void initializeUploadButton(){
        MemoryBuffer buffer = new MemoryBuffer();
        uploadButton = new Upload(buffer);
        uploadButton.setUploadButton(new Button("Bild hochladen"));
        uploadButton.setDropLabel(new Span("Bild-Datei hier reinziehen"));
    }

    public void initializeRadioButton(){
        radioOffer = new RadioButtonGroup();
        radioOffer.setLabel("Was möchten Sie?");
        radioOffer.setItems("Bieten", "Suchen");
    }
}
