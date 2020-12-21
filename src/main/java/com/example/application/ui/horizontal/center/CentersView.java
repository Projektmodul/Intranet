package com.example.application.ui.horizontal.center;

import com.example.application.ui.ContentHolder;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "centers", layout = ContentHolder.class)
@PageTitle("Centers")
public class CentersView extends Div {

    public CentersView() {
        setId("centers-view");
        setClassName("pageContentPosition");
        add(new Text(" Übersicht Center"));
    }

}
