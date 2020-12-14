package com.example.application.ui.vertical.telefonbuch;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Telefonbuch", layout = MainView.class)
@PageTitle("Telefonbuch")
public class TelefonbuchView extends Div {

    public TelefonbuchView() {
        setId("telefonbuch-view");
        add(new Text("Content placeholder"));
    }

}
