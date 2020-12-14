package com.example.application.ui.vertical.fahrplan;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Fahrplan", layout = MainView.class)
@PageTitle("Fahrplan")
public class FahrplanView extends Div {

    public FahrplanView() {
        setId("fahrplan-view");
        add(new Text("Content text replacer"));
    }

}
