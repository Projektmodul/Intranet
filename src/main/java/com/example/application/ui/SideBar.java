package com.example.application.ui;

import com.example.application.ui.vertical.apps.AppsView;
import com.example.application.ui.vertical.calendar.CalendarView;
import com.example.application.ui.vertical.canteen.CanteenView;
import com.example.application.ui.vertical.favorites.FavoritesView;
import com.example.application.ui.vertical.help.HelpView;
import com.example.application.ui.vertical.lastVisited.LastVisitedView;
import com.example.application.ui.vertical.mailing.MailingView;
import com.example.application.ui.vertical.myContacts.MyContactsView;
import com.example.application.ui.vertical.myProfile.MyProfileView;
import com.example.application.ui.vertical.phoneBook.PhoneBookView;
import com.example.application.ui.vertical.search.SearchView;
import com.example.application.ui.vertical.settings.SettingsView;
import com.example.application.ui.vertical.timetable.TimetableView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

import java.util.Arrays;
import java.util.HashMap;


public class SideBar extends Div {

    Tabs tabs;


    public SideBar(){

        addClassName("side-bar");

        tabs = new Tabs();
        Tab[] tabArray = new Tab[13];
        for (int i = 0; i<tabArray.length;i++) {
            tabArray[i] = new Tab("");
        }

        tabArray[0].add(new RouterLink("Suche",SearchView.class));
        tabArray[1].add(new RouterLink("Mein Profil", MyProfileView.class));
        tabArray[2].add(new RouterLink("Telefonbuch", PhoneBookView.class));
        tabArray[3].add(new RouterLink("Einstellungen", SettingsView.class));
        tabArray[4].add(new RouterLink("Hilfe", HelpView.class));
        tabArray[5].add(new RouterLink("Meine Kontakte", MyContactsView.class));
        tabArray[6].add(new RouterLink("Mailing", MailingView.class));
        tabArray[7].add(new RouterLink("Kalender", CalendarView.class));
        tabArray[8].add(new RouterLink("Fahrplan", TimetableView.class));
        tabArray[9].add(new RouterLink("Apps", AppsView.class));
        tabArray[10].add(new RouterLink("Favoriten", FavoritesView.class));
        tabArray[11].add(new RouterLink("Betriebsrestaurant", CanteenView.class));
        tabArray[12].add(new RouterLink("Zuletzt besucht", LastVisitedView.class));

        for (int i=0;i<tabArray.length;i++) {
            tabs.add(tabArray[i]);
        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setFlexGrowForEnclosedTabs(1);

        add(tabs);


    }
}
