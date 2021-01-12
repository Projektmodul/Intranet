package com.example.application.ui.horizontal.library;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "library", layout = MainView.class)
@PageTitle("Bibliothek")
public class LibraryView extends Div {

    public LibraryView() {
        setId("librarView");
        setClassName("pageContentPosition");
        add(new Text("Übersicht Bibliothek"));
        System.out.println("Hello");
    }

}
