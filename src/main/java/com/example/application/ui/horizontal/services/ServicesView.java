package com.example.application.ui.horizontal.services;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "services", layout = MainView.class)
@PageTitle("Services")
public class ServicesView extends Div {

    public ServicesView() {
        setId("serviceView");
        setClassName("pageContentPosition");
        add(new Text("Übersicht Services"));
    }

}
