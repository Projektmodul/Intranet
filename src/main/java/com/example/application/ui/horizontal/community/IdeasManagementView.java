package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.IdeaEntity;
import com.example.application.ui.MainView;
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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

/**
 *  IdeaManagement View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "ideasManagement", layout = MainView.class)
@PageTitle("Ideenmanagement")
public class IdeasManagementView extends Div {

    private IdeaEntity ideaEntity = new IdeaEntity(1," "," "," ");

    public IdeasManagementView() {
        setId("ideasManagement");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        H1 ideaH = new H1("Ideenmanagement");
        ideaH.setId("ideaHead");

        List<IdeaEntity> ideaList = Arrays.asList(
                new IdeaEntity(1,"Überschrift 1","Idee 1","in Bearbeitung"),
                new IdeaEntity(2,"Überschrift 2","Idee 2","prämiert"),
                new IdeaEntity(3,"Überschrift 3","Idee 3","nicht prämiert"));


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

        H4 overViewTable = new H4("Liste der Verbesserungsvorschläge");
        overViewTable.setClassName("overViewTableH");

        Button buttonIdea = new Button("Idee einreichen", new Icon(VaadinIcon.LIGHTBULB));
        buttonIdea.addClickListener(e -> initContentDialog().open());
        buttonIdea.setIconAfterText(true);
        buttonIdea.setId("buttonIdea");

        Label ideas = new Label("Ihre Ideen und Vorschläge sind herzlich willkommen.");
        ideas.setClassName("quote");

        add(ideaH, buttonIdea,ideas,overViewTable, grid);
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
