package com.example.application.ui.vertical.search;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "search", layout = MainView.class)
@PageTitle("Suchen")
public class SearchView extends Div {

    public SearchView() {
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");
        add(new Text("Content placeholder"));
    }

}
