package com.example.application.ui.vertical.favoriten;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Favoriten", layout = MainView.class)
@PageTitle("Favoriten")
public class FavoritenView extends Div {

    public FavoritenView() {
        setId("favoriten-view");
        add(new Text("Content placeholder"));
    }

}
