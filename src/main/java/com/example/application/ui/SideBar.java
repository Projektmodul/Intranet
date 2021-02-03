package com.example.application.ui;


import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.auxiliary.InitData;
import com.example.application.ui.vertical.canteen.CanteenView;
import com.example.application.ui.vertical.help.HelpView;
import com.example.application.ui.vertical.myProfile.MyProfileView;
import com.example.application.ui.vertical.notifications.NotificationsView;
import com.example.application.ui.vertical.phoneBook.PhoneBookView;
import com.example.application.ui.vertical.settings.SettingsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;

/**
 *  SideBar shows ...
 *
 *  @author Lea Schünemann, Vanessa Skowronsky
 *  @version 3.0
 *  @since 16.12.2020
 *  @lastUpdated 02.02.2021 from Vanessa Skowronsky
 */
public class SideBar extends VerticalLayout{

    public Tabs tabs;
    private NotificationsView notificationsView;

    private final Icon alertIcon;
    private int notificationCounter;
    private Span counterSpan;

    public SideBar(LinkService linkService){
        setId("sideBar");


        //insert icon for alertIcon
        alertIcon = new Icon(VaadinIcon.BELL);
        alertIcon.setId("alertBell");

        counterSpan = new Span();
        add(this.counterSpan);
        this.counterSpan.setId("alertSpan");

        Div notificationsAlertIconSpanContainer = new Div(alertIcon,counterSpan);
        notificationsAlertIconSpanContainer.setId("notificationAlertSpan");

        //insert field for search
        TextField searchField = new TextField();
        searchField.setId("search");
        searchField.setPlaceholder("Suchbegriff eingeben...");

        //external links
        Anchor anchorTimetable = new Anchor("https://fahrplaner.vbn.de", "Fahrplan");
        anchorTimetable.setTarget("_blank");

        Anchor anchorMailing = new Anchor("https://email.bsag.de", "Mailing");
        anchorMailing.setTarget("_blank");

        Label telephoneBookLabel = new Label("Telefonbuch");
        telephoneBookLabel.getStyle().set("cursor", "pointer");
        HorizontalLayout phoneBookLayout = new HorizontalLayout(telephoneBookLabel);
        phoneBookLayout.addClickListener(e -> new Dialog(initPhoneBookChoice(linkService)).open());

        tabs = new Tabs();
        Tab[] tabArray = new Tab[14];
        for (int i = 0; i<tabArray.length;i++) {
            tabArray[i] = new Tab("");
        }

        tabArray[0].add(new RouterLink("Mein Profil", MyProfileView.class));
        tabArray[1].add(phoneBookLayout);
        tabArray[2].add(new RouterLink("Einstellungen", SettingsView.class));
        tabArray[3].add(new RouterLink("Hilfe", HelpView.class));
        tabArray[4].add(new RouterLink("Meine Kontakte", InProgressView.class));
        tabArray[5].add(anchorMailing);
        tabArray[6].add(new RouterLink("Kalender", InProgressView.class));
        tabArray[7].add(anchorTimetable);
        tabArray[8].add(new RouterLink("Apps", InProgressView.class));
        tabArray[9].add(new RouterLink("Favoriten", InProgressView.class));
        tabArray[10].add(new RouterLink("Betriebsrestaurant", CanteenView.class));
        tabArray[11].add(new RouterLink("Zuletzt besucht", InProgressView.class));
        tabArray[12].add(new RouterLink("Meine Aufgaben", InProgressView.class));
        tabArray[13].add(new RouterLink("Hinzufügen", InProgressView.class));

        for (Tab tab : tabArray) {
            tabs.add(tab);
        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setSelectedTab(null);
        tabs.setId("tabsView");
        tabs.setThemeName("minimal");

        initializeAlertIcon();

        add(notificationsAlertIconSpanContainer,searchField,tabs);

    }

    public void setEventOfNotificationView(NotificationsView notificationsView){
        alertIcon.addClickListener(e-> notificationsView.open());
    }

    public void setSideBarToNull(){
        tabs.setSelectedTab(null);
    }

    public void setNotificationsView(NotificationsView notificationsView) {
        this.notificationsView = notificationsView;
    }

    public int getNotificationCounter() {
        return notificationCounter;
    }

    public void setNotificationCounter(int notificationCounter) {
        this.notificationCounter = notificationCounter;
    }

    public void setCounterSpan(String counter) {
        this.counterSpan.setText(counter);
        initializeAlertIcon();
    }

    public void initializeAlertIcon(){
        if(notificationCounter == 0){
            alertIcon.setColor("#575757");
            this.counterSpan.setVisible(false);
        }else{
            alertIcon.setColor("#e31313");
            this.counterSpan.setVisible(true);
        }
    }

    private VerticalLayout initPhoneBookChoice(LinkService linkService){
        InitData initPhoneBook = new InitData(linkService);

        VerticalLayout phoneLinks = new VerticalLayout();
        phoneLinks.add(initPhoneBook.setLinkData(6), initPhoneBook.setLinkData(7));
        add(phoneLinks);

        return phoneLinks;
    }
}
