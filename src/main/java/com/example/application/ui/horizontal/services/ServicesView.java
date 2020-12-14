package com.example.application.ui.horizontal.services;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Services", layout = MainView.class)
@PageTitle("Services")
public class ServicesView extends Div {

    public ServicesView() {
        setId("services-view");
        add(new Text("Content placeholder"));
    }

}
