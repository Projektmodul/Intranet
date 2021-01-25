package com.example.application.ui.vertical.settings;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 *  Settings View shows ...
 *
 *  @author Rebecca Schirmacher
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "settings", layout = MainView.class)
@PageTitle("Einstellungen")
public class SettingsView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private H3 pageText;
    private PageEntity pageEntity;
    private Span hell;
    private Span dark;

    public SettingsView(PageService pageService) {
        setId("settings");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        pageEntity = pageService.findPageById(24);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H3(pageEntity.getContent());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        ToggleButton toggleButton = new ToggleButton();
        toggleButton.addClickListener(event -> toggleDarkMode());

        hell = new Span("hell");
        dark = new Span("dunkel");

        HorizontalLayout toogleLayout = new HorizontalLayout();
        toogleLayout.add(hell,toggleButton,dark);

        Div container = new Div();
        container.add(toogleLayout);
        container.setId("container");

        add(breadcrumbs, pageTitle ,pageText ,container);
    }

    private void toggleDarkMode(){
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();

        if (themeList.contains(Lumo.DARK)) {
            themeList.remove(Lumo.DARK);
        } else {
            themeList.add(Lumo.DARK);
        }
    }
}
