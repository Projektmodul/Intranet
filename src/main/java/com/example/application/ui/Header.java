package com.example.application.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./styles/views/main/mainView.css")
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class Header extends VerticalLayout {

    public Header(){
        setId("header");
        setBackgroundColor("home");

        HorizontalBar navBar = new HorizontalBar();

        Image logoImage = new Image("images/bsag.png", "My Project logo");
        logoImage.setId("logoImage");
        logoImage.addClickListener(e-> logoImage.getUI().ifPresent(ui -> ui.navigate("home")));


        Icon logoutIcon = new Icon(VaadinIcon.SIGN_OUT);
        logoutIcon.addClickListener(e-> logoutIcon.getUI().ifPresent(ui -> ui.getPage().setLocation("/logout")));
        logoutIcon.setId("logout");

        HorizontalLayout iconContainer = new HorizontalLayout();
        iconContainer.add(logoImage,logoutIcon);
        iconContainer.setId("iconContainer");
        add(iconContainer,navBar);
    }




    public void setBackgroundColor(String colorName) {

        switch (colorName) {
            case "home": setClassName("colorHome"); break;
            default : break;
        }
    }
}