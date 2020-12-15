package com.example.application.ui;


import com.example.application.ui.horizontal.center.CenterIView;
import com.example.application.ui.horizontal.community.BlogView;
import com.example.application.ui.horizontal.community.IdeasManagementView;
import com.example.application.ui.horizontal.community.NoticeBoardView;
import com.example.application.ui.horizontal.library.*;
import com.example.application.ui.horizontal.ourCompany.*;
import com.example.application.ui.horizontal.projects.NordlichtView;
import com.example.application.ui.horizontal.services.BusinessTripsView;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
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

public class MainView extends VerticalLayout implements RouterLayout {

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
        items = new String[]{"","Unser Unternehmen", "Center", "Projekte", "Bibliothek", "Services", "Community"};
        subItemsOurCompany = new String[]{"Willkommen", "Über Uns", "Nachrichten", "Stellenangebote", "Sport & Freizeit"};
        subItemsCenter = new String[]{"Center I"};
        subItemsProjects = new String[]{"Nordlicht"};
        subItemsLibrary = new String[]{"Wiki", "Unterlagen", "Archiv", "Medien", "FAQ"};
        subItemsServices = new String[]{"Dienstreisen"};
        subItemsCommunity = new String[]{"Blog", "Schwarzes Brett", "Ideenmanagement"};
        menuBar = createMenuItems();
        menuBar.setOpenOnHover(true);

        addClassName("main-view");
        setSizeFull();

        SideBar sideBar = new SideBar();
        sideBar.setSizeFull();

        Div text = new Div();
        text.addClassName("text-div");

        Div content = new Div(text, sideBar);
        content.addClassName("content");
        content.setSizeFull();

        add(createTopBar(header, menuBar), content);

    }

    private VerticalLayout createTopBar(HorizontalLayout header, MenuBar menu) {
        VerticalLayout layout = new VerticalLayout();
        //layout.getThemeList().add("dark");
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

        Icon avatar = new Icon(VaadinIcon.SIGN_OUT);
        //Image avatar = new Image("images/user.svg", "Avatar");
        avatar.setId("avatar");
        header.add(avatar);
        avatar.setSize("2rem");

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
            MenuItem copyItem = menuBar.addItem(items[i]);
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
                createSubMenuItems(copyItem, currentSubMenu,i);
            } else {
                Icon homeLogo = new Icon(VaadinIcon.HOME_O);
                homeLogo.setSize("30px");
                homeLogo.setColor("white");
                copyItem.add(homeLogo);
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


}