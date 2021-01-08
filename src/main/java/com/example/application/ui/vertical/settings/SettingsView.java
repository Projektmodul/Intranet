package com.example.application.ui.vertical.settings;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "settings", layout = ContentHolder.class)
@PageTitle("Einstellungen")
public class SettingsView extends Div {

    public SettingsView() {
        setId("content-view_blue");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
    }

}
