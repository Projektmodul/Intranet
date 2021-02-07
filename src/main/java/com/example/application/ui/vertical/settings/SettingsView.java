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
 *  Settings View displays the settings that a user can change
 *
 *  @author Rebecca Schirmacher, Vanessa Skowronsky Laura Neuendorf, Jessica Reistel
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 07.02.2021 by Jessica Reistel
 */
@Route(value = "settings", layout = MainView.class)
@PageTitle("Einstellungen")
public class SettingsView extends Div {

    private SettingService settingService;
    private SettingEntity settingEntity;

    public SettingsView(PageService pageService, SettingService settingService, UserService userService) {
        this.settingService = settingService;

        setId("settings");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        PageEntity pageEntity = pageService.findPageById(24);
        H1 pageTitle = new H1(pageEntity.getTitle());
        H4 pageText = new H4(pageEntity.getContent());

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        settingEntity = userEntity.getSetting();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        ToggleButton toggleButtonDarkMode = new ToggleButton(settingEntity.getDarkmode());
        toggleButtonDarkMode.addClickListener(event -> {
            toggleDarkMode();
            getUI().ifPresent(ui -> ui.navigate("settings"));
        });
        Span light = new Span("hell");
        Span dark = new Span("dunkel");
        HorizontalLayout toogleLayoutDarkMode = new HorizontalLayout();
        toogleLayoutDarkMode.add(light,toggleButtonDarkMode,dark);

        H4 pageText2 = new H4("Stellen Sie hier die Unternehmensnachrichten ein / aus:");
        ToggleButton toggleButtonNews = new ToggleButton(true);
        Span off = new Span("aus");
        Span on = new Span("ein");
        HorizontalLayout toogleLayoutNews = new HorizontalLayout();
        toogleLayoutNews.add(off,toggleButtonNews,on);

        add(breadcrumbs, pageTitle ,pageText, toogleLayoutDarkMode, pageText2, toogleLayoutNews);
    }

    private void toggleDarkMode(){
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();

        if (settingEntity.getDarkmode()) {
            themeList.remove(Lumo.DARK);
            settingService.update(settingEntity, false);
        } else {
            themeList.add(Lumo.DARK);
            settingService.update(settingEntity, true);
        }
    }
}
