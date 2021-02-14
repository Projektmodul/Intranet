package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.NoticeBoardOfferEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.noticeBoard.NoticeBoardOfferService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;


/**
 * NoticeBoard View shows all offers from the database
 *
 *  @author Monika Martius, Jessica Reistel
 *  @version 5.0
 *  @since 19.01.2021
 *  @lastUpdated 08.02.2021 by Monika Martius
 */
@Route(value = "noticeBoard", layout = MainView.class)
@PageTitle("Schwarzes Brett")

public class NoticeBoardView extends Div {
    private final UserService userService;
    private final H1 pageTitle;
    private final Paragraph pageText;
    private Upload uploadButton;
    private RadioButtonGroup radioOffer;
    private final List<NoticeBoardOfferEntity> noticeBoardList;
    private Grid<NoticeBoardOfferEntity> noticeBoardGrid;
    private Component leftComponent;
    private Component rightComponent;
    private final Breadcrumbs breadcrumbs;

    public NoticeBoardView(PageService pageService, NoticeBoardOfferService noticeBoardOfferService,UserService userService) {
        setId("noticeBoard");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        this.userService = userService;

        int maxId = noticeBoardOfferService.findMaxId();
        PageEntity pageEntity = pageService.findPageById(20);
        noticeBoardList = new ArrayList<>();
        for(int i= 1;i<=maxId;i++){
            if(noticeBoardOfferService.findById(i) != null){
                NoticeBoardOfferEntity noticeBoardOfferEntity = noticeBoardOfferService.findById(i);
                noticeBoardList.add(noticeBoardOfferEntity);
            }
        }
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Paragraph(pageEntity.getContent());

        breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        initializeGrid();
        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    /**
     * The method initializes a Vertical Layout with pageText,boxIcon,noticeBoardGrid
     */
    public void initializeLeftContainer() {
        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        int role = roleEntity.getRoleId();

        Div box = new Div();
        box.add(breadcrumbs, pageTitle);
        box.setId("layoutBox");

        Div boxIcon = new Div();
        Icon deleteIcon = new Icon(VaadinIcon.TRASH);
        deleteIcon.setId("deleteIcon");
        Icon editIcon = new Icon(VaadinIcon.EDIT);
        editIcon.setId("editIcon");
        boxIcon.add(editIcon,deleteIcon);

        if (role != 1){
            boxIcon.setVisible(false);
        }

        this.add(box);
        leftComponent = new VerticalLayout(pageText,boxIcon,noticeBoardGrid);
        leftComponent.setId("leftLayout");
    }

    /**
     * The method initializes the Grid with all offers
     */
    public void initializeGrid(){
        noticeBoardGrid = new Grid<>();
        noticeBoardGrid.setItems(noticeBoardList);
        noticeBoardGrid.setSelectionMode(Grid.SelectionMode.NONE);
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getCategory, "Suche/Biete").setHeader("Suche/Biete").setFooter("Gesamt: " + noticeBoardList.size());
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getTitle, "Titel").setHeader("Titel");
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getDescription).setHeader("Beschreibung");
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getPrice, "Preis").setHeader("Preis");
        noticeBoardGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
    }

    /**
     * The method initializes a Vertical Layout with radioOffer,textTitle,textArea,price,uploadButton,toAdd
     */
    public void initializeRightContainer(){
        initializeUploadButton();
        initializeRadioButton();
        IntegerField price = new IntegerField("Preis");
        TextField textTitle = new TextField("Titel");
        TextArea textArea = new TextArea("Beschreibung");
        textArea.setId("styleTextArea");
        textArea.setPlaceholder("Anzeige aufgeben ...");
        Button toAdd = new Button();
        toAdd.setText("Hinzufügen");
        toAdd.setId("layoutSetID");
        rightComponent = new VerticalLayout(radioOffer,textTitle,textArea,price,uploadButton, toAdd);
        rightComponent.getElement().getStyle().set("width", "50%");
        rightComponent.setId("rightComponent");
    }

    /**
     * The method initializes a Horizontal Layout with two vertical Layouts
     */
    public void initializeSplitLayout(){
        HorizontalLayout layoutSplit = new HorizontalLayout(leftComponent, rightComponent);
        layoutSplit.setId("splitLayout");
        this.add(layoutSplit);
    }

    /**
     * The method initializes a Upload with a button and a Span
     */
    public void initializeUploadButton(){
        MemoryBuffer buffer = new MemoryBuffer();
        uploadButton = new Upload(buffer);
        uploadButton.setUploadButton(new Button("Bild hochladen"));
        uploadButton.setDropLabel(new Span("Bild-Datei hier reinziehen"));
    }

    /**
     * The method initializes a RadioButtonGroup with a label and items
     */
    public void initializeRadioButton(){
        radioOffer = new RadioButtonGroup();
        radioOffer.setLabel("Was möchten Sie?");
        radioOffer.setItems("Bieten", "Suchen");
    }
}
