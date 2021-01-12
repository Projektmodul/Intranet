package com.example.application.ui.horizontal.projects;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "projects", layout = MainView.class)
@PageTitle("Projekte")
public class ProjectsView extends Div {

    public ProjectsView() {
        setId("projektView");
        setClassName("pageContentPosition");
        add(new Text("Übersicht Projekte"));
    }
}
