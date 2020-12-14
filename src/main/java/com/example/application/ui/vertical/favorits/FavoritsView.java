package com.example.application.ui.vertical.favorits;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "favorits", layout = MainView.class)
@PageTitle("Favoriten")
public class FavoritsView extends Div {

    public FavoritsView() {
        setId("favorits-view");
        add(new Text("Content placeholder"));
    }

}
