package com.example.application.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "inProgress", layout = ContentHolder.class)
@PageTitle("Im Aufbau")
public class InProgressView extends Div {

    public InProgressView(){
        setId("content-view_blue");
        setClassName("pageContentPosition");
        add(new Text("Entschuldigung! Diese Seite befindet sich noch im Aufbau."));
    }
}
