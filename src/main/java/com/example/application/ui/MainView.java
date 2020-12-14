package com.example.application.ui;

import com.example.application.ui.horizontal.bibliothek.*;
import com.example.application.ui.horizontal.centers.Center1View;
import com.example.application.ui.horizontal.centers.CentersView;
import com.example.application.ui.horizontal.community.BlogView;
import com.example.application.ui.horizontal.community.CommunityView;
import com.example.application.ui.horizontal.community.IdeasManagementView;
import com.example.application.ui.horizontal.community.NoticeBoardView;
import com.example.application.ui.horizontal.projekte.NordlichtView;
import com.example.application.ui.horizontal.projekte.ProjekteView;
import com.example.application.ui.horizontal.services.BusinessTripsView;
import com.example.application.ui.horizontal.services.ServicesView;
import com.example.application.ui.horizontal.unserunternehmen.*;
import com.example.application.ui.vertical.apps.AppsView;
import com.example.application.ui.vertical.betriebsrestaurant.BetriebsrestaurantView;
import com.example.application.ui.vertical.einstellungen.EinstellungenView;
import com.example.application.ui.vertical.fahrplan.FahrplanView;
import com.example.application.ui.vertical.favoriten.FavoritenView;
import com.example.application.ui.vertical.hilfe.HilfeView;
import com.example.application.ui.vertical.kalendar.KalendarView;
import com.example.application.ui.vertical.mailing.MailingView;
import com.example.application.ui.vertical.meinProfil.MeinProfilView;
import com.example.application.ui.vertical.meineKontakte.MeineKontakteView;
import com.example.application.ui.vertical.searching.SearchingView;
import com.example.application.ui.vertical.telefonbuch.TelefonbuchView;
import com.example.application.ui.vertical.zuletztBesucht.ZuletztBesuchtView;
import com.helger.commons.state.ILeftRightIndicator;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
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
@PWA(name = "My Project", shortName = "My Project", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends AppLayout {

    private static MenuBar menuBar;
    private static String[] items;
    private static String[] subItemsUnserUnternehmen;
    private static String[] subItemsCenters;
    private static String[] subItemsProjekte;
    private static String[] subItemsBibliothek;
    private static String[] subItemsServices;
    private static String[] subItemsCommunity;


    private static final Class<? extends com.vaadin.flow.component.Component>[][] navigationMatrix = new Class[][]{
            {MainView.class},
            {WelcomeView.class, AboutUsView.class, NewsView.class, CareerView.class, SportView.class},
            {Center1View.class},
            {NordlichtView.class},
            {WikiView.class, DocumentsView.class, ArchiveView.class, MediaView.class, FAQView.class},
            {BusinessTripsView.class},
            {BlogView.class, NoticeBoardView.class, IdeasManagementView.class}
    };


    public MainView() {
        HorizontalLayout header = createHeader();
        items = new String[]{"","Unser Unternehmen", "Centers", "Projekte", "Bibliothek", "Services", "Community"};
        subItemsUnserUnternehmen = new String[]{"Willkommen", "Über Uns", "Nachrichten", "Stellenangebote", "Sport & Freizeit"};
        subItemsCenters = new String[]{"Center I"};
        subItemsProjekte = new String[]{"Nordlicht"};
        subItemsBibliothek = new String[]{"Wiki", "Unterlagen", "Archive", "Medien", "FAQ"};
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
                        currentSubMenu = subItemsUnserUnternehmen;
                        break;
                    case 2:
                        currentSubMenu = subItemsCenters;
                        break;
                    case 3:
                        currentSubMenu = subItemsProjekte;
                        break;
                    case 4:
                        currentSubMenu = subItemsBibliothek;
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
        addToDrawer(new VerticalLayout(
                new RouterLink("Suche", SearchingView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Mein Profil", MeinProfilView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Telefonbuch", TelefonbuchView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Einstellungen", EinstellungenView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Hilfe", HilfeView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Meine Kontakte", MeineKontakteView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Mailing", MailingView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Kalendar", KalendarView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Fahrplan", FahrplanView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Apps", AppsView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Favoriten", FavoritenView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Betriebsrestaurant", BetriebsrestaurantView.class)
        ));

        addToDrawer(new VerticalLayout(
                new RouterLink("Zuletzt besucht", ZuletztBesuchtView.class)
        ));
    }
}
