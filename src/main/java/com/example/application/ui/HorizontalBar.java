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
 * HorizontalBarView shows a menubar with submenu items that open after hovering over them.
 * All icons are clickable and route to the clicked page.
 * A listener for the communication between HorizontalBar and SideBar is added.
 * A mockup chat functionality is shown.
 *
 * @author Monika Martius, Vanessa Skowronsky
 * @version 6.0
 * @since 17.12.2020
 * @lastUpdated 01.02.2021 by Vanessa Skowronsky
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

        initOurCompanyMenu();
        initCentersMenu();
        initProjectsMenu();
        initLibraryMenu();
        initServicesMenu();
        initCommunityMenu();

        Icon chat = new Icon(VaadinIcon.CHAT);
        chat.setSize("35px");
        chat.setColor("#FFFFFF");
        chat.addClickListener(e -> new Dialog(initChat()).open());
        addItem(chat);
    }

    private void initOurCompanyMenu() {
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
    }

    private void initCentersMenu() {
        VerticalLayout layoutCenterI = createSubMenu(new Icon(VaadinIcon.CLUSTER), "#0A5396", "Center I", "centerI");

        ArrayList<VerticalLayout> subMenuListCenter = new ArrayList<>();
        subMenuListCenter.add(layoutCenterI);
        createMenuItem(subMenuListCenter, "Center", "centers");
    }

    private void initProjectsMenu() {
        VerticalLayout layoutNordlicht = createSubMenu(new Icon(VaadinIcon.TRAIN), "#581092", "Nordlicht", "nordlicht");

        ArrayList<VerticalLayout> subMenuListProjects = new ArrayList<>();
        subMenuListProjects.add(layoutNordlicht);
        createMenuItem(subMenuListProjects, "Projekte", "projects");
    }

    private void initLibraryMenu() {
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
    }

    private void initServicesMenu() {
        VerticalLayout layoutTime = createSubMenu(new Icon(VaadinIcon.CHART_TIMELINE), "#FF5621", "Zeitkonto", "inProgress");

        VerticalLayout layoutLSA = createSubMenu(new Icon(VaadinIcon.PYRAMID_CHART), "#FF5621", "LSA-Meldungen", "inProgress");

        VerticalLayout layoutDrive = createSubMenu(new Icon(VaadinIcon.CAR), "#FF5621", "Fahrdienst", "inProgress");

        VerticalLayout layoutBusinessTrip = createSubMenu(new Icon(VaadinIcon.FLIGHT_TAKEOFF), "#FF5621", "Dienstreisen", "inProgress");

        VerticalLayout layoutFormDesigner = createSubMenuLink(new Icon(VaadinIcon.FORM), "#FF5621", "Formular Designer", "https://www.microsoft.com/de-de/microsoft-365/online-surveys-polls-quizzes");

        ArrayList<VerticalLayout> subMenuListServices = new ArrayList<>();
        subMenuListServices.add(layoutTime);
        subMenuListServices.add(layoutLSA);
        subMenuListServices.add(layoutDrive);
        subMenuListServices.add(layoutBusinessTrip);
        subMenuListServices.add(layoutFormDesigner);
        createMenuItem(subMenuListServices, "Services", "services");
    }

    private void initCommunityMenu() {
        VerticalLayout layoutBlog = createSubMenuLink(new Icon(VaadinIcon.PENCIL), "#F0D12C", "Blog", "https://blog.bsag.de/");

        VerticalLayout layoutNoticeBoard = createSubMenu(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#F0D12C", "Schwarzes Brett", "noticeBoard");

        VerticalLayout layoutIdeaManagement = createSubMenu(new Icon(VaadinIcon.HANDS_UP), "#F0D12C", "Ideenmanagement", "ideasManagement");

        ArrayList<VerticalLayout> subMenuListCommunity = new ArrayList<>();
        subMenuListCommunity.add(layoutBlog);
        subMenuListCommunity.add(layoutNoticeBoard);
        subMenuListCommunity.add(layoutIdeaManagement);
        createMenuItem(subMenuListCommunity, "Community", "community");
    }

    /**
     * Methods creates a submenu tab which links to an external site and opens it on a different browser tab
     * @param icon Icon that is shown
     * @param backgroundColor Background color of the vertical layout
     * @param spanText Title of the tab
     * @param href URL where the tab links to
     * @return Submenu tab as a vertical layout
     */
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

    /**
     * Methods creates a submenu tab which navigates to the indicated class
     * @param icon Icon that is shown
     * @param backgroundColor Background color of the vertical layout
     * @param spanText Title of the tab
     * @param route View to navigate to after clicking
     * @return Submenu tab as a vertical layout
     */
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

    /**
     * Method creates a menu item with extended submenu tabs
     * @param layoutList List of all submenu tabs
     * @param labelText Title of the menu item
     * @param route View to navigate to after clicking
     */
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

    /**
     * Method initiates a chat component wiht mockup messages for demonstration only
     * @return Chat component with mockup messages
     */
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

    /**
     * Method creates a list with messages for the chat component
     * @return List with messages
     */
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

    public HorizontalBarClickedInitiator getInitiator() {
        return initiator;
    }
}