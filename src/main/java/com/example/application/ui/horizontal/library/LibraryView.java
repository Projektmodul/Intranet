package com.example.application.ui.horizontal.library;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "library", layout = MainView.class)
@PageTitle("Bibliothek")
public class LibraryView extends Div {

    public LibraryView() {
        setId("library-view");
        setClassName("pageContentPosition");
        add(new Text("Bibliothek"));
        System.out.println("Hello");
    }

}
