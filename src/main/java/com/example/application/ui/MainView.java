/*created @ de Boer, Marieke Menna & Monika Martius */
package com.example.application.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/mainView.css")
@PWA(name = "BSAG Intranet", shortName = "BSAG Intranet", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends VerticalLayout implements RouterLayout {

    public MainView() {
        /*
         * Header
         * */
        setId("MainView");
         //create the object navBar from class HorizontalBar
        HorizontalBar navBar = new HorizontalBar();

        Image logoImage = new Image("images/bsag.png", "My Project logo");
        logoImage.setId("logoImage");

        NativeButton logoButton = new NativeButton("");
        logoButton.addClickListener( e-> logoButton.getUI().ifPresent(ui -> ui.navigate("home")));

        logoButton.add(logoImage);
        logoButton.setId("logoButton");

        Icon logoutIcon = new Icon(VaadinIcon.SIGN_OUT);
        logoutIcon.setId("logout");
        logoutIcon.addClickListener(e-> logoutIcon.getUI().ifPresent(ui -> ui.getPage().setLocation("/logout")));

        HorizontalLayout header = new HorizontalLayout();
        header.add(logoButton,logoutIcon);
        header.setId("header");

        add(header, navBar);
        addClassName("vertical-layout");

    }

}