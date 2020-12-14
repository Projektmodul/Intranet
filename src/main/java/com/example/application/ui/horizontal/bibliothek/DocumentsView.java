package com.example.application.ui.horizontal.bibliothek;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Unterlagen", layout = MainView.class)
@PageTitle("Unterlagen")
public class DocumentsView extends Div {

    public DocumentsView() {
        setId("documents-view");
        add(new Text("Unterlagen"));
    }

}
