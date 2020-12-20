/*created @ de Boer, Marieke Menna & Monika Martius */
package com.example.application.ui;

import com.vaadin.flow.component.applayout.AppLayout;
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
@Route(value = "my-view", layout = MainView.class)
@PageTitle("My View")

public class MainView extends AppLayout {

    public MainView() {

        /*
         * Header
         * */
         //create the object navBar from class HorizontalBar
        HorizontalBar navBar = new HorizontalBar();
        navBar.setClassName("layoutNavbar");
        Image bsagLogo = new Image("images/bsag.png", "My Project logo");
        bsagLogo.setClassName("bsagStyle");

        NativeButton logoBtn = new NativeButton("");
        logoBtn.addClickListener( e-> logoBtn.getUI().ifPresent(ui -> ui.navigate("")));

        logoBtn.add(bsagLogo);
        logoBtn.setClassName("bsagBtnStyle");

        Icon logout = new Icon(VaadinIcon.SIGN_OUT);
        logout.setClassName("logoutStyle");

        SideBar sideBar = new SideBar();
        sideBar.setClassName("layoutSideBar");

        Div text = new Div();
        text.addClassName("text-div");
        Div content = new Div(text);
        content.addClassName("content");

        HorizontalLayout sideBarLayout = new HorizontalLayout();
        sideBarLayout.add(content,sideBar);

        //create two horizontal Header for topBar
        HorizontalLayout headerOne = new HorizontalLayout();
        headerOne.add(logoBtn,logout);
        HorizontalLayout headerTwo = new HorizontalLayout();
        headerTwo.add(navBar);

        VerticalLayout layout = new VerticalLayout();
        layout.add(headerOne, headerTwo,sideBarLayout);
        layout.addClassName("vertical-layout");
        VerticalLayout topBar = new VerticalLayout();
        topBar.add(layout);

        add(topBar);



    }


}