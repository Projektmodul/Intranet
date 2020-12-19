package com.example.application.ui.horizontal.services;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "dienstreisen", layout = MainView.class)
@PageTitle("Dienstreisen")
public class BusinessTripsView extends Div {

    public BusinessTripsView() {
        setId("businessTrips-view");
        setClassName("pageContentPosition");
        add(new Text("Dienstreisen"));
    }

}
