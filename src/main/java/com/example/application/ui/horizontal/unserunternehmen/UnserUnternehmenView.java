package com.example.application.ui.horizontal.unserunternehmen;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "UnserUnternehmen", layout = MainView.class)
@PageTitle("Unser Unternehmen")
@RouteAlias(value = "", layout = MainView.class)
public class UnserUnternehmenView extends Div {

    public UnserUnternehmenView() {
        setId("unser-unternehmen-view");
        add(new Text("Content placeholder"));
    }
}
