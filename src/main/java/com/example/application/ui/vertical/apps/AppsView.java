package com.example.application.ui.vertical.apps;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "apps", layout = MainView.class)
@PageTitle("Apps")
public class AppsView extends Div {

    public AppsView() {
        setId("apps-view");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
    }

} //Jessica war hier :)
