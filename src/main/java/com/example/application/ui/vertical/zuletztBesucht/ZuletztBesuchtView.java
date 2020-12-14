package com.example.application.ui.vertical.zuletztBesucht;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "ZuletztBesucht", layout = MainView.class)
@PageTitle("Zuletzt Besucht")
public class ZuletztBesuchtView extends Div {

    public ZuletztBesuchtView() {
        setId("zuletztBesucht-view");
        add(new Text("Content placeholder "));
    }

}
