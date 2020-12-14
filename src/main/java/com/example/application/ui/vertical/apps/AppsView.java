package com.example.application.ui.vertical.apps;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Apps", layout = MainView.class)
@PageTitle("Apps")
public class AppsView extends Div {

    public AppsView() {
        setId("apps-view");
        add(new Text("Content placeholder"));
    }

}
