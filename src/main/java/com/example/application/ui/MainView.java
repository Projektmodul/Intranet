package com.example.application.ui;

import com.example.application.ui.horizontal.library.*;
import com.example.application.ui.horizontal.center.CenterIView;
import com.example.application.ui.horizontal.community.BlogView;
import com.example.application.ui.horizontal.community.IdeasManagementView;
import com.example.application.ui.horizontal.community.NoticeBoardView;
import com.example.application.ui.horizontal.projects.NordlichtView;
import com.example.application.ui.horizontal.services.BusinessTripsView;
import com.example.application.ui.horizontal.ourCompany.*;
import com.example.application.ui.vertical.apps.AppsView;
import com.example.application.ui.vertical.canteen.CanteenView;
import com.example.application.ui.vertical.settings.SettingsView;
import com.example.application.ui.vertical.timetable.TimetableView;
import com.example.application.ui.vertical.favorits.FavoritsView;
import com.example.application.ui.vertical.help.HelpView;
import com.example.application.ui.vertical.calendar.CalendarView;
import com.example.application.ui.vertical.mailing.MailingView;
import com.example.application.ui.vertical.myProfile.MyProfileView;
import com.example.application.ui.vertical.myContacts.MyContactsView;
import com.example.application.ui.vertical.search.SearchView;
import com.example.application.ui.vertical.phoneBook.PhoneBookView;
import com.example.application.ui.vertical.lastVisited.LastVisitedView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/main-view.css")
@PWA(name = "BSAG Intranet", shortName = "BSAG Intranet", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends AppLayout {

    private static MenuBar menuBar;
    private static String[] items;
    private static String[] subItemsOurCompany;
    private static String[] subItemsCenter;
    private static String[] subItemsProjects;
    private static String[] subItemsLibrary;
    private static String[] subItemsServices;
    private static String[] subItemsCommunity;


    private static final Class<? extends com.vaadin.flow.component.Component>[][] navigationMatrix = new Class[][]{
            {MainView.class},
            {WelcomeView.class, AboutUsView.class, NewsView.class, CareerView.class, SportView.class},
            {CenterIView.class},
            {NordlichtView.class},
            {WikiView.class, DocumentsView.class, ArchiveView.class, MediaView.class, FAQView.class},
            {BusinessTripsView.class},
            {BlogView.class, NoticeBoardView.class, IdeasManagementView.class}
    };


    public MainView() {
        HorizontalLayout header = createHeader();
        items = new String[]{"","Unser Unternehmen", "Centers", "Projekte", "Bibliothek", "Services", "Community"};
        subItemsOurCompany = new String[]{"Willkommen", "Über Uns", "Nachrichten", "Stellenangebote", "Sport & Freizeit"};
        subItemsCenter = new String[]{"Center I"};
        subItemsProjects = new String[]{"Nordlicht"};
        subItemsLibrary = new String[]{"Wiki", "Unterlagen", "Archive", "Medien", "FAQ"};
        subItemsServices = new String[]{"Dienstreisen"};
        subItemsCommunity = new String[]{"Blog", "Schwarzes Brett", "Ideenmanagement"};
        menuBar = createMenuItems();
        menuBar.setOpenOnHover(true);
        addToNavbar(createTopBar(header, menuBar));
        createDrawer();
    }

    private VerticalLayout createTopBar(HorizontalLayout header, MenuBar menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(header, menu);
        layout.addClassName("vertical-layout");

        return layout;
    }

    private HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setPadding(false);
        header.setSpacing(false);
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setId("header");
        Image logo = new Image("images/logo.png", "My Project logo");
        logo.setHeight("auto");
        logo.setWidth("22rem");
        logo.addClassName("logo");
        logo.setId("logo");
        header.add(logo);

        Icon signOutIcon = new Icon(VaadinIcon.SIGN_OUT);
        //Image signOutIcon = new Image("images/user.svg", "Avatar");
        signOutIcon.setId("signOutIcon");
        signOutIcon.setSize("2rem");
        header.add(signOutIcon);

        return header;
    }

    private static MenuBar createMenuItems() {
        menuBar = new MenuBar();
        menuBar.getStyle().set("max-width", "100%");
        menuBar.addClassName("navBar");
        getAvailableMenuItems();
        return menuBar;
    }

    private static void getAvailableMenuItems() {
        for (int i=0; i < items.length; i++) {
            MenuItem copy_item = menuBar.addItem(items[i]);
            copy_item.setId("menu_item");
            if (i > 0) {
                //Stream.of(items[i]).forEach(menuBar::addItem);
                String[] currentSubMenu = new String[1];
                switch (i) {
                    case 1:
                        currentSubMenu = subItemsOurCompany;
                        break;
                    case 2:
                        currentSubMenu = subItemsCenter;
                        break;
                    case 3:
                        currentSubMenu = subItemsProjects;
                        break;
                    case 4:
                        currentSubMenu = subItemsLibrary;
                        break;
                    case 5:
                        currentSubMenu = subItemsServices;
                        break;
                    case 6:
                        currentSubMenu = subItemsCommunity;
                        break;
                    default:
                        System.out.println("SubMenu not found!");
                       // menuBar.setOpenOnHover(false);
                }
                createSubMenuItems(copy_item, currentSubMenu,i);
            } else {
                Icon home_logo = new Icon(VaadinIcon.HOME_O);
                home_logo.setSize("30px");
                home_logo.setColor("white");
                copy_item.add(home_logo);
            }

        }

    }


    private static void createSubMenuItems(MenuItem menuItem, String[] subItems, int navMenuCount) {
        SubMenu subMenu = menuItem.getSubMenu();
          for (int j=0; j < navigationMatrix[navMenuCount].length; j++) {
              // MenuItem subItem = subMenu.addItem(subItems[i]);
              subMenu.add(new RouterLink(subItems[j], navigationMatrix[navMenuCount][j]));
          }
    }

    private void createDrawer() {
    /*    RouterLink listLink = new RouterLink("", MainView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());*/
        addToDrawer(new VerticalLayout(new RouterLink("Suche", SearchView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Mein Portal", MyProfileView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Telefonbuch", PhoneBookView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Einstellungen", SettingsView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Hilfe", HelpView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Meine Kontakte", MyContactsView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Mailing", MailingView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Kalendar", CalendarView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Fahrplan", TimetableView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Apps", AppsView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Favoriten", FavoritsView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Betriebsrestaurant", CanteenView.class)));

        addToDrawer(new VerticalLayout(new RouterLink("Zuletzt besucht", LastVisitedView.class)));
    }
}
