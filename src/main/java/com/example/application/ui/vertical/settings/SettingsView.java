package com.example.application.ui.vertical.settings;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.SettingEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.settings.SettingService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.componentfactory.ToggleButton;
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
    private Span light;
    private Span dark;
    private SettingService settingService;
    private SettingEntity settingEntity;

    private UserEntity userEntity;
    private UserService userService;
    private ToggleButton toggleButton;


    public SettingsView(PageService pageService, SettingService settingService, UserService userService) {
        setId("settings");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.settingService = settingService;
        this.userService = userService;

        GetUserController getUserController = new GetUserController();

        pageEntity = pageService.findPageById(24);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H3(pageEntity.getContent());

        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        settingEntity = userEntity.getSetting();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        //ToggleButton toggleButton;
        if (UI.getCurrent().getElement().getThemeList().contains(Lumo.DARK)) {
            toggleButton = new ToggleButton(true);
            toggleButton.setValue(settingEntity.getDarkmode());
        } else {
            toggleButton = new ToggleButton(false);
            toggleButton.setValue(settingEntity.getDarkmode());
        }

        toggleButton.addClickListener(event -> {
            toggleDarkMode();
            getUI().ifPresent(ui -> ui.navigate("settings"));
        });

        light = new Span("hell");
        dark = new Span("dunkel");

        HorizontalLayout toogleLayout = new HorizontalLayout();
        toogleLayout.add(light,toggleButton,dark);

        Div container = new Div();
        container.add(toogleLayout);
        container.setId("container");

        add(breadcrumbs, pageTitle ,pageText ,container);
    }

    private void toggleDarkMode(){
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();

        if (themeList.contains(Lumo.DARK)) {
            themeList.remove(Lumo.DARK);
            settingService.update(settingEntity, toggleButton);
            getStyle().set("background-color", "white");
            getStyle().set("color", "hsl(214, 35%, 21%)");
        } else {
            themeList.add(Lumo.DARK);
            settingService.update(settingEntity, toggleButton);
            getStyle().set("background-color", "hsl(214, 35%, 21%)");
            getStyle().set("color", "white");
        }

    }
}
