package com.example.application.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@RouteAlias(value = "", layout = ContentHolder.class)
@Route(value = "home", layout = ContentHolder.class)
@PageTitle("BSAG Intranet")

public class HomeView extends Div {

    public HomeView() {
        setId("content-view_blue");
        setClassName("pageContentPosition");
        add(new Text("HomeView"));
    }
}