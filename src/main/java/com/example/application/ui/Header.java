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

@CssImport("./styles/views/main/header.css")
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class Header extends VerticalLayout {

    public Header(){
        setClassName("verticalLayout");

        HorizontalBar navBar = new HorizontalBar();

        Image logoImage = new Image("images/bsag.png", "My Project logo");
        logoImage.setId("logoImage");

        NativeButton logoButton = new NativeButton("");
        logoButton.addClickListener( e-> logoButton.getUI().ifPresent(ui -> ui.navigate("home")));

        logoButton.add(logoImage);
        logoButton.setId("logoButton");

        Icon logoutIcon = new Icon(VaadinIcon.SIGN_OUT);
        logoutIcon.setId("logout");

        HorizontalLayout header = new HorizontalLayout();
        header.add(logoButton,logoutIcon);
        header.setId("header");
        add(header,navBar);
    }
}
