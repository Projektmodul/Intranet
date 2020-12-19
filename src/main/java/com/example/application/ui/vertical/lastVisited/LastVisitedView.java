package com.example.application.ui.vertical.lastVisited;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "lastVisited", layout = MainView.class)
@PageTitle("Zuletzt Besucht")
public class LastVisitedView extends Div {

    public LastVisitedView() {
        setId("lastVisited-view");
        add(new Text("Content placeholder "));
    }

}
