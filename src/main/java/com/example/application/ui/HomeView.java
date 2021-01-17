package com.example.application.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 *  Home View shows ...
 *
 *  @author Vanessa Skowronsky
 *  @version 2.0
 *  @since 04.01.2021
 *  @lastUpdated 17.01.2021
 */
@RouteAlias(value = "", layout = MainView.class)
@Route(value = "home", layout = MainView.class)
@PageTitle("BSAG Intranet")

public class HomeView extends Div {

    public HomeView() {
        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        add(new H1("Herzlich Willkommen"));
    }
}