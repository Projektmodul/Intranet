package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.*;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.noticeBoard.NoticeBoardOfferService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.roles.RoleService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.Image;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudEditorPosition;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @author Monika Martius, Jessica Reistel
 *  @version 3.0
 *  @since 19.01.2021
 *  @lastUpdated 24.01.2021
 */
@Route(value = "noticeBoard", layout = MainView.class)
@PageTitle("Schwarzes Brett")

public class NoticeBoardView extends Div {
    private NoticeBoardOfferEntity noticeBoardOfferEntity;
    private NoticeBoardOfferService noticeBoardOfferService;
    private UserService userService;
    private H1 pageTitle;
    private Span pageText;
    private PageEntity pageEntity;
    private Upload uploadButton;
    private HorizontalLayout layoutSplit;
    private RadioButtonGroup radioOffer;
    private Button toAdd;
    private List<NoticeBoardOfferEntity> noticeBoardList;
    private Grid<NoticeBoardOfferEntity> noticeBoardGrid;
    private PageService pageService;
    private Component leftComponent;
    private Component rightComponent;
    private Breadcrumbs breadcrumbs;

    public NoticeBoardView(PageService pageService, NoticeBoardOfferService noticeBoardOfferService,UserService userService) {
        this.noticeBoardOfferService = noticeBoardOfferService;
        this.pageService = pageService;
        this.userService = userService;
        setId("noticeBoard");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        int maxId = noticeBoardOfferService.findMaxId();
        pageEntity = this.pageService.findPageById(20);
        noticeBoardList = new ArrayList<>();
        for(int i= 1;i<=maxId;i++){
            if(this.noticeBoardOfferService.findById(i) != null){
                noticeBoardOfferEntity = this.noticeBoardOfferService.findById(i);
                noticeBoardList.add(noticeBoardOfferEntity);
            }
        }
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Span(pageEntity.getContent());

        breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        initializeGrid();
        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

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

        leftComponent = new VerticalLayout(box, pageText,boxIcon,noticeBoardGrid);
        leftComponent.setId("leftLayout");
    }

    public void initializeGrid(){
        noticeBoardGrid = new Grid<>();
        noticeBoardGrid.setItems(noticeBoardList);
        noticeBoardGrid.setSelectionMode(Grid.SelectionMode.NONE);
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getCategory, "Suche/Biete").setHeader("Suche/Biete");
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getTitle, "Titel").setHeader("Titel");
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getDescription).setHeader("Beschreibung");
        noticeBoardGrid.addColumn(NoticeBoardOfferEntity::getPrice, "Preis").setHeader("Preis");
    }

    public void initializeRightContainer(){
        initializeUploadButton();
        initializeRadioButton();
        IntegerField price = new IntegerField("Preis");
        TextField textTitle = new TextField("Titel");
        TextArea textArea = new TextArea("Beschreibung");
        textArea.setId("styleTextArea");
        textArea.setPlaceholder("Anzeige aufgeben ...");
        toAdd = new Button();
        toAdd.setText("Hinzufügen");
        toAdd.setId("layoutSetID");
        rightComponent = new VerticalLayout(radioOffer,textTitle,textArea,price,uploadButton,toAdd);
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
