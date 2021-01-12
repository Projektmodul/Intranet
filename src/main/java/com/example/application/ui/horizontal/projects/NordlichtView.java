package com.example.application.ui.horizontal.projects;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "nordlicht", layout = MainView.class)
@PageTitle("Nordlicht")
public class NordlichtView extends Div {

    public NordlichtView() {
        setId("projektView");
        setClassName("pageContentPosition");
        add(new Text("Nordlicht"));
    }

}
