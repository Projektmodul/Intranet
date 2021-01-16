package com.example.application.ui.vertical.settings;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Settings View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "settings", layout = MainView.class)
@PageTitle("Einstellungen")
public class SettingsView extends Div {

    public SettingsView() {
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");
        add(new Text("Content placeholder"));
    }

}
