package com.example.application.ui.horizontal.bibliothek;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Bibliothek", layout = MainView.class)
@PageTitle("Bibliothek")
public class BibliothekView extends Div {

    public BibliothekView() {
        setId("bibliothek-view");
        add(new Text("Bibliothek"));
        System.out.println("Hello");
    }

}
