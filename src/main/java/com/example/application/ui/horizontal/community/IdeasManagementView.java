package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.IdeaEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.ideas.IdeaService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
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

import java.util.Arrays;
import java.util.List;

/**
 *  IdeaManagement View shows ...
 *
 *  @author Litharshiga Sivarasa, Jessica Reistel
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated 25.01.2021
 */
@Route(value = "ideasManagement", layout = MainView.class)
@PageTitle("Ideenmanagement")
public class IdeasManagementView extends Div {

    private PageService pageService;
    private IdeaService ideaService;
    private PageEntity pageEntity;
    private IdeaEntity ideaEntity;
    private IdeaEntity ideaEntity1;
    private IdeaEntity ideaEntity2;

    public IdeasManagementView(PageService pageService, IdeaService ideaService) {
        this.pageService = pageService;
        this.ideaService = ideaService;

        setId("ideasManagement");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        pageEntity = pageService.findPageById(21);

        H1 title = new H1(pageEntity.getTitle());

        ideaEntity = ideaService.findById(1);
        ideaEntity1 = ideaService.findById(2);
        ideaEntity2 = ideaService.findById(3);
        List<IdeaEntity> ideaList = Arrays.asList(ideaEntity, ideaEntity1, ideaEntity2);


        /*
         *Create a grid bound to the list
         */
        Grid<IdeaEntity> grid = new Grid<>();
        grid.setItems(ideaList);
        grid.addColumn(IdeaEntity::getTitle).setHeader("Überschrift");
        grid.addColumn(IdeaEntity::getDescription).setHeader("Idee");
        grid.addColumn(IdeaEntity::getRating).setHeader("Status");
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS,GridVariant.LUMO_ROW_STRIPES,GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.getStyle().set("border", "2px solid #a6a6a6");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setId("tableIdea");

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button buttonIdea = new Button("Idee einreichen", new Icon(VaadinIcon.LIGHTBULB));
        buttonIdea.addClickListener(e -> initContentDialog().open());
        buttonIdea.setIconAfterText(true);

        Label ideas = new Label(pageEntity.getContent());
        ideas.setClassName("quote");

        horizontalLayout.add(ideas, buttonIdea);

        add(title, horizontalLayout, grid);
    }

    /*
     * The method initContentDialog generates a dialog window
     * @return contentDialog
     */
    private Dialog initContentDialog() {
        Dialog contentDialog = new Dialog();
        contentDialog.setCloseOnOutsideClick(false);
        contentDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();
        saveCancel.setId("saveCancelDiv");

        Button saveButton = new Button("Idee einreichen");
        Button cancelButton = new Button("Abbrechen", e -> contentDialog.close());
        saveButton.addClassName("ideaButton");
        cancelButton.addClassName("ideaButton");
        saveCancel.add(saveButton, cancelButton);

        HorizontalLayout update = new HorizontalLayout();


        update.addComponentAsFirst(ideaUpdate());
        contentDialog.add(new H3("Ideen einreichen"), update, saveCancel);

        return contentDialog;
    }

    /*
     * The method ideaUpdate generates the vertical layout for idea update
     * @return updateIdea
     */
    private VerticalLayout ideaUpdate() {
        VerticalLayout updateIdea = new VerticalLayout();

        TextField updateTitle = new TextField();
        updateTitle.setValue(ideaEntity.getTitle());
        updateTitle.setLabel("Überschrift");

        TextArea updateDescription = new TextArea();
        updateDescription.setValue(ideaEntity.getDescription());
        updateDescription.setLabel("Idee");

        updateIdea.addComponentAsFirst(updateTitle);
        updateIdea.addComponentAtIndex(1, updateDescription);

        return updateIdea;
    }

}
