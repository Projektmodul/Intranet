package com.example.application.ui.horizontal.projects;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.OverviewComponents;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Projects View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Jessica Reistel
 */
@Route(value = "projects", layout = MainView.class)
@PageTitle("Projekte")
public class ProjectsView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 firstQuote;
    private H2 secondQuote;
    private HorizontalLayout layout;

    public ProjectsView(PageService pageService) {
        this.pageService = pageService;

        setId("projects");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        setContent();

        add(breadcrumbs,pageTitle, firstQuote, secondQuote, layout);
    }

    private void setContent(){
        PageEntity pageEntity = pageService.findPageById(11);

        pageTitle = new H1(pageEntity.getTitle());
        firstQuote = new H2(pageEntity.getContent());
        secondQuote = new H2("Überseestadt");
        firstQuote.setClassName("firstQuote");
        secondQuote.setClassName("secondQuote");

        layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setId("scroll");
        layout.addClassName("justifyContentCenter");

        Icon iconAdd = new Icon(VaadinIcon.ADD_DOCK);
        iconAdd.addClickListener(e -> initDialogAdd().open());

        Component componentNordlicht = OverviewComponents.createComponent(new Icon(VaadinIcon.TRAIN), "#581092", "Nordlicht", "nordlicht");
        Component componentAdd = OverviewComponents.createComponent(iconAdd, "#581092", "Projekt hinzufügen","projects");

        layout.add(componentAdd, componentNordlicht);

    }

    private Dialog initDialogAdd(){
        Dialog addDialog = new Dialog();
        addDialog.setCloseOnOutsideClick(false);
        addDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();
        saveCancel.setId("saveCancelDiv");
        Button saveButton = new Button("Projekt einreichen", e -> {addProject(); addDialog.close();});
        Button cancelButton = new Button("Abbrechen", e -> addDialog.close());
        saveButton.addClassName("ideaButton");
        cancelButton.addClassName("ideaButton");
        saveCancel.add(saveButton, cancelButton);

        addDialog.add(new H3("Projekt einreichen"), saveCancel);

        return addDialog;
    }

    private Div addProject(){
        Div componentNewProject = new Div();

        Component componentNew = OverviewComponents.createComponent(new Icon(VaadinIcon.CAR), "#581092", "Neues E-Auto", "projects");
        layout.add(componentNew);

        return componentNewProject;
    }


}