package com.example.application.ui.horizontal.projects;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Projekte", layout = MainView.class)
@PageTitle("Projekte")
public class ProjectsView extends Div {

    public ProjectsView() {
        setId("projects-view");
        setClassName("pageContentPosition");
        add(new Text("Projekte"));
    }
}
