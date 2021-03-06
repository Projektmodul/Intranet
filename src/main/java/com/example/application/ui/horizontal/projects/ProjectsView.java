package com.example.application.ui.horizontal.projects;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
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
 *  Projects View is the overview page of the projects. Here you can add a dummy project
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky, Laura Neuendorf, Monika Martius
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 14.02.2021 by Vanessa Skowronsky
 */
@Route(value = "projects", layout = MainView.class)
@PageTitle("Projekte")
public class ProjectsView extends Div {
    private final PageService pageService;
    private final UserService userService;
    private PageEntity pageEntity;

    private H1 pageTitle;
    private H2 firstQuote;
    private H2 secondQuote;
    private HorizontalLayout layout;

    public ProjectsView(PageService pageService, UserService userService) {
        this.pageService = pageService;
        this.userService = userService;

        setId("projects");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        setContent();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle, firstQuote, secondQuote, layout);
    }

    /**
     * This method fetches the data from the database
     * and displays it on the corresponding page
     */
    private void setContent(){
        pageEntity = pageService.findPageById(11);

        pageTitle = new H1(pageEntity.getTitle());
        firstQuote = new H2(pageEntity.getContent());
        secondQuote = new H2("Überseestadt");
        firstQuote.setClassName("firstQuote");
        secondQuote.setClassName("secondQuote");

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        int role = roleEntity.getRoleId();

        layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setId("scroll");
        layout.addClassName("justifyContentCenter");

        Icon iconAdd = new Icon(VaadinIcon.ADD_DOCK);
        iconAdd.addClickListener(e -> initDialogAdd().open());

        Component componentNordlicht = OverviewComponents.createComponent(new Icon(VaadinIcon.TRAIN), "#7626b5", "Nordlicht", "nordlicht");
        Component componentAdd = OverviewComponents.createComponent(iconAdd, "#7626b5", "Projekt hinzufügen","projects");

        if(role==1){
            layout.add(componentAdd);
        }

        layout.add(componentNordlicht);
    }

    /**
     * This method creates a content dialog window to add a new project
     * @return addDialog as dialog
     */
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

    /**
     * This method creates a new dummy component named "Neues E-Auto"
     * @return componentNewProject as div
     */
    private Div addProject(){
        Div componentNewProject = new Div();

        Component componentNew = OverviewComponents.createComponent(new Icon(VaadinIcon.CAR), "#7626b5", "Neues E-Auto", "projects");
        layout.add(componentNew);

        return componentNewProject;
    }


}