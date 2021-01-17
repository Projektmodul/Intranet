package com.example.application.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  InProgress View shows ...
 *
 *  @author Vanessa Skowronsky
 *  @version 1.0
 *  @since 04.01.2021
 *  @lastUpdated 17.01.2021
 */
@Route(value = "inProgress", layout = MainView.class)
@PageTitle("Im Aufbau")
public class InProgressView extends Div {

    public InProgressView(){
        setId("inProgress");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");
        add(new Text("Entschuldigung! Diese Seite befindet sich noch im Aufbau."));
    }
}
