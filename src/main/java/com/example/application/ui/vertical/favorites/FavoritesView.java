package com.example.application.ui.vertical.favorites;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "favorits", layout = MainView.class)
@PageTitle("Favoriten")
public class FavoritesView extends Div {
    TextField textfield = new TextField("Add Text");

    public FavoritesView() {
        setId("favorits-view");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"), textfield);

    }

}
