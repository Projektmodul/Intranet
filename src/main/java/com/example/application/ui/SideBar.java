package com.example.application.ui;

import com.example.application.backend.services.links.LinkService;
import com.example.application.ui.auxiliary.InitData;
import com.example.application.ui.vertical.canteen.CanteenView;
import com.example.application.ui.vertical.help.HelpView;
import com.example.application.ui.vertical.myProfile.MyProfileView;
import com.example.application.ui.vertical.notifications.ZeroNotificationsDialog;
import com.example.application.ui.vertical.settings.SettingsView;
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
 *  SideBar shows a notification bell, a search field and tabs, which navigate to different side views,
 *  to external links or open a dialog
 *
 *  @author Lea Schünemann, Vanessa Skowronsky
 *  @version 4.0
 *  @since 16.12.2020
 *  @lastUpdated 04.02.2021 by Sabrine Gamdou
 */
public class SideBar extends VerticalLayout{
    public Tabs tabs;
    private Dialog notificationsView;
    private final Icon alertIcon;
    private int notificationCounter;
    private final Span counterSpan;

    public SideBar(LinkService linkService){
        setId("sideBar");

        alertIcon = new Icon(VaadinIcon.BELL);
        alertIcon.setId("alertBell");
        counterSpan = new Span();
        add(this.counterSpan);
        this.counterSpan.setId("alertSpan");
        Div notificationsAlertIconSpanContainer = new Div(alertIcon,counterSpan);
        notificationsAlertIconSpanContainer.setId("notificationAlertSpan");

        TextField searchField = new TextField();
        searchField.setId("search");
        searchField.setPlaceholder("Suchbegriff eingeben...");

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

        for (Tab tab: tabArray) {
            tab.getStyle().set("color", "white");
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

    public void setEventOfNotificationView(Dialog notificationsView){
        alertIcon.addClickListener(e-> notificationsView.open());
    }

    public void setSideBarToNull(){
        tabs.setSelectedTab(null);
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
            this.counterSpan.setVisible(false);
            notificationsView = new ZeroNotificationsDialog();
        }else{
            this.counterSpan.setVisible(true);
        }
    }

    private VerticalLayout initPhoneBookChoice(LinkService linkService){
        Icon telephoneIcon = new Icon(VaadinIcon.PHONE);
        telephoneIcon.setSize("3rem");

        InitData initPhoneBook = new InitData(linkService);

        VerticalLayout phoneLinks = new VerticalLayout();
        phoneLinks.add(telephoneIcon);
        phoneLinks.add(initPhoneBook.setLinkData(6), initPhoneBook.setLinkData(7));
        phoneLinks.getElement().getStyle().set("align-items", "center");

        add(phoneLinks);

        return phoneLinks;
    }
}
