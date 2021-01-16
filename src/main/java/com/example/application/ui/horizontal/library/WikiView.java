package com.example.application.ui.horizontal.library;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "wiki", layout = MainView.class)
@PageTitle("Wiki")
public class WikiView extends Div {
    public WikiView() {
        setId("wiki-view");
        setClassName("pageContentPosition");
        add(new Text("Wiki"));
    }
}
