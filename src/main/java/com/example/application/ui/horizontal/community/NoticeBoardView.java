package com.example.application.ui.horizontal.community;

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

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Monika Martius
 *  @version 1.0
 *  @since 19.01.2021
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
    private List<Objects> objectList = new ArrayList<>();

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

        objectList.add(new Objects(100, "Wohnung", Status.SUCHE, 450, "liliad@gmail.com"));
        objectList.add(new Objects(101, "Wohnung", Status.BIETE, 300, "miamaia@gmail.com"));
        objectList.add(new Objects(102, "Katze", Status.SUCHE, 0, "ldfsdfdfd@gmail.com"));
        objectList.add(new Objects(103, "Auto", Status.SUCHE, 3000, "nnfdgfg@gmail.com"));
        objectList.add(new Objects(104, "Wohnung", Status.SUCHE, 450, "liliad@gmail.com"));
        objectList.add(new Objects(105, "Wohnung", Status.BIETE, 300, "miamaia@gmail.com"));
        objectList.add(new Objects(106, "Katze", Status.SUCHE, 0, "ldfsdfdfd@gmail.com"));
        objectList.add(new Objects(107, "Auto", Status.BIETE, 500, "nnfdgfg@gmail.com"));
        objectList.add(new Objects(108, "Wohnung", Status.SUCHE, 450, "liliad@gmail.com"));
        objectList.add(new Objects(109, "Wohnung", Status.BIETE, 300, "miamaia@gmail.com"));
        objectList.add(new Objects(110, "Katze", Status.BIETE, 10, "ldfsdfdfd@gmail.com"));
        objectList.add(new Objects(111, "Auto", Status.SUCHE, 3000, "nnfdgfg@gmail.com"));

        Grid<Objects> grid = new Grid<>(Objects.class);
        grid.setItems(objectList);
        grid.removeColumnByKey("id");

        leftComponent = new VerticalLayout(editDeleteIcon,grid,pageContent);
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
