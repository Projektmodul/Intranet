package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.IdeaEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.ideas.IdeaService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

/**
 *  IdeaManagement View shows all ideas from the database
 *
 *  @author Litharshiga Sivarasa, Jessica Reistel, Vanessa Skowronsky
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 10.02.2021 by Jessica Reistel
 */
@Route(value = "ideasManagement", layout = MainView.class)
@PageTitle("Ideenmanagement")
public class IdeasManagementView extends Div {
    private PageService pageService;
    private PageEntity pageEntity;

    private final IdeaService ideaService;

    public IdeasManagementView(PageService pageService, IdeaService ideaService) {
        setId("ideasManagement");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        this.pageService = pageService;
        this.ideaService = ideaService;

        Button buttonIdea = new Button("Idee einreichen", new Icon(VaadinIcon.LIGHTBULB));
        buttonIdea.addClickListener(e -> initDialogAdd().open());
        buttonIdea.setIconAfterText(true);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb("Ideenmanagement"));

        InitData initIdeas = new InitData(pageService);
        this.add(breadcrumbs, initIdeas.setData(21), buttonIdea,  initializeGrid());
    }

    /**
     * The method initializes the Grid with all ideas
     * @return grid
     */
    public Grid initializeGrid(){
        List<IdeaEntity> ideaList = new ArrayList<>();
        int maxId = ideaService.findMaxId();
        IdeaEntity ideaEntity;
        for(int i=1 ; i<=maxId ; i++){
            if(ideaService.findById(i) != null){
                ideaEntity = ideaService.findById(i);
                ideaList.add(ideaEntity);
            }
        }

        Grid<IdeaEntity> grid = new Grid<>();
        grid.setItems(ideaList);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(IdeaEntity::getTitle, "Titel").setHeader("Titel").setFooter("Gesamt: "+ ideaList.size());
        grid.addColumn(IdeaEntity::getDescription, "Idee").setHeader("Idee");
        grid.addColumn(IdeaEntity::getRating, "Status").setHeader("Status");
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setId("gridFullPage");

        return grid;
    }

    /*
     * The method initDialogAdd generates a dialog window to add an idea
     * @return addDialog
     */
    private Dialog initDialogAdd() {
        Dialog addDialog = new Dialog();
        addDialog.setCloseOnOutsideClick(false);
        addDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();
        saveCancel.setId("saveCancelDiv");
        Button saveButton = new Button("Idee einreichen");
        Button cancelButton = new Button("Abbrechen", e -> addDialog.close());
        saveButton.addClassName("ideaButton");
        cancelButton.addClassName("ideaButton");
        saveCancel.add(saveButton, cancelButton);

        HorizontalLayout update = new HorizontalLayout();
        update.addComponentAsFirst(ideaAdd());
        addDialog.add(new H3("Ideen einreichen"), update, saveCancel);

        return addDialog;
    }

    /*
     * The method ideaAdd generates the vertical layout to add a idea
     * @return addIdea
     */
    private VerticalLayout ideaAdd() {
        VerticalLayout addIdea = new VerticalLayout();

        TextField newTitle = new TextField();
        newTitle.setPlaceholder("Ideetitel");
        newTitle.setLabel("Titel");

        TextArea newDescription = new TextArea();
        newDescription.setPlaceholder("Beschreibung");
        newDescription.setLabel("Idee");

        addIdea.addComponentAsFirst(newTitle);
        addIdea.addComponentAtIndex(1, newDescription);

        return addIdea;
    }

}
