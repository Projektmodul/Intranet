package com.example.application.ui.vertical.settings;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "settings", layout = MainView.class)
@PageTitle("Einstellungen")
public class SettingsView extends Div {

    public SettingsView() {
        setId("settings-view");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
    }

}
