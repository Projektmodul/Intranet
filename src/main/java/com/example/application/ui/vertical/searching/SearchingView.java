package com.example.application.ui.vertical.searching;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Suchen", layout = MainView.class)
@PageTitle("Suchen")
public class SearchingView extends Div {

    public SearchingView() {
        setId("searching-view");
        add(new Text("Content placeholder"));
    }

}
