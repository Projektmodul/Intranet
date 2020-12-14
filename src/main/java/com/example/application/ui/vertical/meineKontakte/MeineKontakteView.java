package com.example.application.ui.vertical.meineKontakte;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "MeineKontakte", layout = MainView.class)
@PageTitle("Meine Kontakte")
public class MeineKontakteView extends Div {

    public MeineKontakteView() {
        setId("meineKontakte-view");
        add(new Text("Content placeholder"));
    }
}