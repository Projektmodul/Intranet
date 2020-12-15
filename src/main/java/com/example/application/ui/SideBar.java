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
import java.util.HashMap;


public class SideBar extends Div {

    Tabs tabs;


    public SideBar(){

        addClassName("side-bar");

        tabs = new Tabs();
        HashMap<Tab,Class> tabHashMap = new HashMap<>();
        tabHashMap.put(new Tab("Suche"), SearchView.class);
        tabHashMap.put(new Tab("Mein Profil"), MyProfileView.class);
        tabHashMap.put(new Tab("Telefonbuch"), PhoneBookView.class);
        tabHashMap.put(new Tab("Einstellungen"), SettingsView.class);
        tabHashMap.put(new Tab("Hilfe"), HelpView.class);
        tabHashMap.put(new Tab("Meine Kontakte"), MyContactsView.class);
        tabHashMap.put(new Tab("Mailing"), MailingView.class);
        tabHashMap.put(new Tab("Kalender"), CalendarView.class);
        tabHashMap.put(new Tab("Fahrplan"), TimetableView.class);
        tabHashMap.put(new Tab("Apps"), AppsView.class);
        tabHashMap.put(new Tab("Favoriten"), FavoritesView.class);
        tabHashMap.put(new Tab("Betriebsrestaurant"), CanteenView.class);
        tabHashMap.put(new Tab("Zuletzt besucht"), LastVisitedView.class);


        for (HashMap.Entry<Tab, Class> entry : tabHashMap.entrySet()) {
            tabs.add(entry.getKey());
        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setFlexGrowForEnclosedTabs(1);

        add(tabs);

        addRoutes(tabHashMap);

    }

    private void addRoutes(HashMap<Tab,Class> tabHashMap) {

        for (HashMap.Entry<Tab, Class> entry : tabHashMap.entrySet()) {
            // MenuItem subItem = subMenu.addItem(subItems[i]);
           add(new RouterLink(entry.getKey().getLabel(),entry.getValue()));
        }
    }
}
