package com.example.application.ui.vertical.settings;

import com.example.application.ui.MainView;
import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 *  Settings View shows ...
 *
 *  @author Rebecca Schirmacher
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "settings", layout = MainView.class)
@PageTitle("Einstellungen")
public class SettingsView extends Div {

    public SettingsView() {
        setId("settings");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");
        add(new Text("Stellen Sie hier ein helles oder dunkles Farbschema ein:"));

        ToggleButton toggleButton = new ToggleButton();
        toggleButton.addClickListener(event -> toggleDarkMode());
        add(toggleButton);
    }

    private void toggleDarkMode(){
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();

        if (themeList.contains(Lumo.DARK)) {
            themeList.remove(Lumo.DARK);
        } else {
            themeList.add(Lumo.DARK);
        }
    }
}
