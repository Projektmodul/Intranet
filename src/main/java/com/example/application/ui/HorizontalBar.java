package com.example.application.ui;

import com.example.application.ui.auxiliary.HorizontalBarClickedInitiator;
import com.vaadin.componentfactory.Chat;
import com.vaadin.componentfactory.model.Message;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;

import java.util.ArrayList;

/**
 * HorizontalBarView is shows a menubar with submenu items that open after hovering over them.
 * All icons are clickable and route to the clicked page.
 *
 * @author Monika Martius, Vanessa Skowronsky
 * @version 5.0
 * @lastUpdated 26.01.2021 by Vanessa Skowronsky
 * @since 17.12.2020
 */
public class HorizontalBar extends MenuBar {

    private HorizontalBarClickedInitiator initiator;
    private Anchor anchorSubMenuLink;
    private ArrayList<Message> messageList;

    public HorizontalBar() {
        setId("horizontalBar");
        setOpenOnHover(true);

        initiator = new HorizontalBarClickedInitiator();


        Icon home = new Icon(VaadinIcon.HOME);
        home.setSize("35px");
        home.setColor("#FFFFFF");
        home.addClickListener(e -> home.getUI().ifPresent(ui -> ui.navigate("home")));
        addItem(home);

        /*
         *  ourCompany
         *              */
        VerticalLayout layoutWelcome = createSubMenu(new Icon(VaadinIcon.HANDSHAKE), "#A00505", "Willkommen", "welcome");

        VerticalLayout layoutAboutUs = createSubMenu(new Icon(VaadinIcon.INFO), "#A00505", "Über uns", "aboutUs");

        VerticalLayout layoutNews = createSubMenu(new Icon(VaadinIcon.NEWSPAPER), "#A00505", "Nachrichten", "news");

        VerticalLayout layoutSport = createSubMenu(new Icon(VaadinIcon.EXIT), "#A00505", "Sport & Freizeit", "sport");

        VerticalLayout layoutJobs = createSubMenu(new Icon(VaadinIcon.DOLLAR), "#A00505", "Stellenangebote", "career");

        ArrayList<VerticalLayout> subMenuListCompany = new ArrayList<>();
        subMenuListCompany.add(layoutWelcome);
        subMenuListCompany.add(layoutAboutUs);
        subMenuListCompany.add(layoutNews);
        subMenuListCompany.add(layoutSport);
        subMenuListCompany.add(layoutJobs);
        createMenuItem(subMenuListCompany, "Unser Unternehmen", "ourCompany");

        /*
         * Centers
         *          */
        //creating icons
        VerticalLayout layoutCenterI = createSubMenu(new Icon(VaadinIcon.CLUSTER), "#0A5396", "Center I", "centerI");

        ArrayList<VerticalLayout> subMenuListCenter = new ArrayList<>();
        subMenuListCenter.add(layoutCenterI);
        createMenuItem(subMenuListCenter, "Center", "centers");

        /*
         * Projects
         *          */
        VerticalLayout layoutNordlicht = createSubMenu(new Icon(VaadinIcon.TRAIN), "#581092", "Nordlicht", "nordlicht");

        ArrayList<VerticalLayout> subMenuListProjects = new ArrayList<>();
        subMenuListProjects.add(layoutNordlicht);
        createMenuItem(subMenuListProjects, "Projekte", "projects");

        /*
         * Library
         *          */
        VerticalLayout layoutDocuments = createSubMenu(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#2F7C78", "Unterlagen", "documents");

        VerticalLayout layoutWiki = createSubMenuLink(new Icon(VaadinIcon.BOOK), "#2F7C78", "Wiki", "https://de.wikipedia.org/wiki/Bremer_Stra%C3%9Fenbahn_AG");

        VerticalLayout layoutArchive = createSubMenu(new Icon(VaadinIcon.ARCHIVE), "#2F7C78", "Archiv", "archive");

        VerticalLayout layoutMedia = createSubMenu(new Icon(VaadinIcon.FILM), "#2F7C78", "Medien", "media");

        VerticalLayout layoutFAQ = createSubMenu(new Icon(VaadinIcon.QUESTION), "#2F7C78", "FAQ", "fAQ");

        ArrayList<VerticalLayout> subMenuListLibrary = new ArrayList<>();
        subMenuListLibrary.add(layoutDocuments);
        subMenuListLibrary.add(layoutWiki);
        subMenuListLibrary.add(layoutArchive);
        subMenuListLibrary.add(layoutMedia);
        subMenuListLibrary.add(layoutFAQ);
        createMenuItem(subMenuListLibrary, "Bibliothek", "library");

        /*
         * Services
         *          */

        VerticalLayout layoutTime = createSubMenu(new Icon(VaadinIcon.CHART_TIMELINE), "#FF5621", "Zeitkonto", "inProgress");

        VerticalLayout layoutLSA = createSubMenu(new Icon(VaadinIcon.PYRAMID_CHART), "#FF5621", "LSA-Meldungen", "inProgress");

        VerticalLayout layoutDrive = createSubMenu(new Icon(VaadinIcon.CAR), "#FF5621", "Fahrdienst", "inProgress");

        VerticalLayout layoutBusinessTrip = createSubMenu(new Icon(VaadinIcon.FLIGHT_TAKEOFF), "#FF5621", "Dienstreisen", "inProgress");

        ArrayList<VerticalLayout> subMenuListServices = new ArrayList<>();
        subMenuListServices.add(layoutTime);
        subMenuListServices.add(layoutLSA);
        subMenuListServices.add(layoutDrive);
        subMenuListServices.add(layoutBusinessTrip);
        createMenuItem(subMenuListServices, "Services", "services");


        /*
         * Community
         *          */
        VerticalLayout layoutBlog = createSubMenuLink(new Icon(VaadinIcon.PENCIL), "#F0D12C", "Blog", "https://blog.bsag.de/");

        VerticalLayout layoutNoticeBoard = createSubMenu(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#F0D12C", "Schwarzes Brett", "noticeBoard");

        VerticalLayout layoutIdeaManagement = createSubMenu(new Icon(VaadinIcon.HANDS_UP), "#F0D12C", "Ideenmanagement", "ideasManagement");


        ArrayList<VerticalLayout> subMenuListCommunity = new ArrayList<>();
        subMenuListCommunity.add(layoutBlog);
        subMenuListCommunity.add(layoutNoticeBoard);
        subMenuListCommunity.add(layoutIdeaManagement);
        createMenuItem(subMenuListCommunity, "Community", "community");

        Icon chat = new Icon(VaadinIcon.CHAT);
        chat.setSize("35px");
        chat.setColor("#FFFFFF");
        chat.addClickListener(e -> new Dialog(initChat()).open());
        addItem(chat);

    }

    private VerticalLayout createSubMenuLink(Icon icon, String backgroundColor, String spanText, String href) {

        icon.setClassName("horizontalBarIcons");

        Span span = new Span(spanText);
        span.setClassName("spanStyle");

        VerticalLayout layout = new VerticalLayout(span, icon);

        layout.setClassName("submenu");
        layout.getStyle().set("background-color", backgroundColor);


        anchorSubMenuLink = new Anchor(href, layout);
        anchorSubMenuLink.setTarget("_blank");
        anchorSubMenuLink.setClassName("submenuLink");
        anchorSubMenuLink.getStyle().set("color", backgroundColor);

        VerticalLayout subMenuLinkContainer = new VerticalLayout(anchorSubMenuLink);
        subMenuLinkContainer.setClassName("submenuLink");

        return subMenuLinkContainer;
    }

    private VerticalLayout createSubMenu(Icon icon, String backgroundColor, String spanText, String route) {

        icon.setClassName("horizontalBarIcons");
        Tab tab = new Tab(icon);


        Span span = new Span(spanText);
        span.setClassName("spanStyle");

        VerticalLayout layout = new VerticalLayout(span, tab);

        layout.addClickListener(e -> {
            initiator.horizontalBarClicked();
            layout.getUI().ifPresent(ui -> ui.navigate(route));
        });

        layout.setClassName("submenu");
        layout.getStyle().set("background-color", backgroundColor);

        return layout;

    }

    private void createMenuItem(ArrayList<VerticalLayout> layoutList, String labelText, String route) {

        HorizontalLayout layout = new HorizontalLayout();

        for (VerticalLayout l : layoutList) {
            layout.add(l);
        }
        layout.setSpacing(true);

        Label label = new Label(labelText);
        label.getStyle().set("color", "white");
        MenuItem menuItem = addItem(label);
        menuItem.addClickListener(e -> {
            initiator.horizontalBarClicked();
            layout.getUI().ifPresent(ui -> ui.navigate(route));
        });
        menuItem.getSubMenu().addItem(layout);

    }

    public HorizontalBarClickedInitiator getInitiator() {
        return initiator;
    }

    private Chat initChat() {
        Chat chatComponent = new Chat();
        chatComponent.setMessages(loadMessages());
        chatComponent.setDebouncePeriod(200);
        chatComponent.setLazyLoadTriggerOffset(2500);
        chatComponent.scrollToBottom();

        chatComponent.addChatNewMessageListener(event -> {
            event.getSource().addNewMessage(new Message(event.getMessage(), "", "Ria Meier", true));
            event.getSource().clearInput();
            event.getSource().scrollToBottom();
        });

        return chatComponent;
    }

    private ArrayList<Message> loadMessages() {
        messageList = new ArrayList<>();
        Message messageOne = new Message("Guten Morgen!", "", "Peter Lustig", false);
        Message messageTwo = new Message("Moin!", "", "Ria Meier", true);
        Message messageThree = new Message("Der neue Speiseplan ist da :-)", "", "Peter Lustig", false);

        messageList.add(messageOne);
        messageList.add(messageTwo);
        messageList.add(messageThree);

        return messageList;
    }


}

