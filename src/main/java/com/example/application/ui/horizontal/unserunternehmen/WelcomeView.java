package com.example.application.ui.horizontal.unserunternehmen;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Willkommen", layout = MainView.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    public WelcomeView() {
        setId("welcome-view");
        add(new Text("Willkommen"));
    }

}
