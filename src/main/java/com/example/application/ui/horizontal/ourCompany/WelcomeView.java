package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "welcome", layout = ContentHolder.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    public WelcomeView() {
        setId("welcome-view");
        setClassName("pageContentPosition");
        add(new Text("Willkommen"));
    }

}
