package com.example.application.ui;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
/**
 *  Header shows ...
 *
 *  @author Monika Martius, Vanessa Skowronsky
 *  @version 2.0
 *  @since 12.01.2021
 *  @lastUpdated 18.01.2021
 */
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class Header extends VerticalLayout {

    private HorizontalBar horizontalBar;

    public Header(){
        setId("header");

        horizontalBar = new HorizontalBar();

        Image logoImage = new Image("images/bsag.png", "My Project logo");
        logoImage.setId("logoImage");
        logoImage.addClickListener(e-> logoImage.getUI().ifPresent(ui -> ui.navigate("home")));


        Icon logoutIcon = new Icon(VaadinIcon.SIGN_OUT);
        logoutIcon.addClickListener(e-> logoutIcon.getUI().ifPresent(ui -> ui.getPage().setLocation("/logout")));
        logoutIcon.setId("logout");

        HorizontalLayout iconContainer = new HorizontalLayout();
        iconContainer.add(logoImage,logoutIcon);
        iconContainer.setId("iconContainer");
        add(iconContainer, horizontalBar);
    }

    public HorizontalBar getHorizontalBar() {
        return horizontalBar;
    }


    public void setBackgroundColor(String colorName) {

        switch (colorName) {
            case "HomeView":
            case "search":
            case "myProfile":
            case "phoneBook":
            case "settings":
            case "help":
            case "InProgressView":
            case "mailing":
            case "canteen":
                            setClassName("colorHome"); break;
            case "ourCompany": setClassName("colorCompany"); break;
            case "center": setClassName("colorCenter"); break;
            case "projects": setClassName("colorProjects"); break;
            case "library": setClassName("colorLibrary"); break;
            case "services": setClassName("colorServices"); break;
            case "community": setClassName("colorCommunity"); break;
            default : break;
        }
    }
}