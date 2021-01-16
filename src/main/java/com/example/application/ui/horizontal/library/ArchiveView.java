package com.example.application.ui.horizontal.library;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "archive", layout = MainView.class)
@PageTitle("Archiv")
public class ArchiveView extends Div {

    public ArchiveView() {
        setId("archive-view");
        setClassName("pageContentPosition");
        add(new Text("Archiv"));
    }

}
