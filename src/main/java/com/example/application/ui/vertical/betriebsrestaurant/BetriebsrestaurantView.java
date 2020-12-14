package com.example.application.ui.vertical.betriebsrestaurant;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Betriebsrestaurant", layout = MainView.class)
@PageTitle("Betriebsrestaurant")
public class BetriebsrestaurantView extends Div {

    public BetriebsrestaurantView() {
        setId("betriebsrestaurant-view");
        add(new Text("Content placeholder"));
    }

}
