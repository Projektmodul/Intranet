package com.example.application.ui.vertical.einstellungen;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Einstellungen", layout = MainView.class)
@PageTitle("Einstellungen")
public class EinstellungenView extends Div {

    public EinstellungenView() {
        setId("einstellungen-view");
        add(new Text("Content placeholder"));
    }

}
