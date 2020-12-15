package com.example.application.ui.vertical.canteen;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "canteen", layout = MainView.class)
@PageTitle("Betriebsrestaurant")
public class CanteenView extends Div {

    public CanteenView() {
        setId("canteen-view");
        add(new Text("Ich habe hunger!!!"));
    }

}
