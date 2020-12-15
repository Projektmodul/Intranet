package com.example.application.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class SideBar extends Div {

    Tabs tabs = new Tabs();
    Tab tab1 = new Tab("Suche");
    Tab tab2 = new Tab("Mein Profil");
    Tab tab3 = new Tab("Telefonbuch");
    Tab tab4 = new Tab("Einstellungen");
    Tab tab5 = new Tab("Hilfe");
    Tab tab6 = new Tab("Meine Kontakte");
    Tab tab7 = new Tab("Mailing");
    Tab tab8 = new Tab("Kalender");
    Tab tab9 = new Tab("Fahrplan");
    Tab tab10 = new Tab("Apps");
    Tab tab11 = new Tab("Favoriten");
    Tab tab12 = new Tab("Betriebsrestaurant");
    Tab tab13 = new Tab("Zuletzt besucht");

    public SideBar(){

        addClassName("side-bar");

        tabs.add(tab1,tab2,tab3,tab4,tab5,tab6,tab7,tab8,tab9,tab10,tab11,tab12,tab13);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setFlexGrowForEnclosedTabs(1);

        add(tabs);

    }
}
