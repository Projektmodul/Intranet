package com.example.application.ui;

import com.example.application.backend.entities.SettingEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.auxiliary.HorizontalBarClickedListener;
import com.example.application.ui.presenters.NotificationPresenter;
import com.example.application.ui.vertical.notifications.NotificationCounterChangedListener;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 *  MainView shows ...
 *
 *  @author Sabrine Gamdou, Anastasiya Jackwerth, Monika Martius, Vanessa Skowronsky
 *  @version 6.0
 *  @since 15.12.2020
 *  @lastUpdated 02.02.2021 by Vanessa Skowronsky
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/mainView.css")
@CssImport("./styles/views/main/content.css")
@PWA(name = "BSAG Intranet", shortName = "BSAG Intranet", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends VerticalLayout implements RouterLayout, HorizontalBarClickedListener, NotificationCounterChangedListener {

    private SettingEntity settingEntity;
    private UserService userService;
    private VerticalLayout contentHolder = new VerticalLayout();
    private Header header;
    private  SideBar sidebar;
    GetUserController getUserController;
    String username;

    private UserEntity userEntity;

    //bidirectional communication between ContentHolder and NotificationPresenter
    private final NotificationPresenter notificationPresenter;

    public MainView(NotificationPresenter notificationPresenter, UserService userService, LinkService linkService) {
        this.notificationPresenter = notificationPresenter;
        this.userService = userService;

        getUserController = new GetUserController();

        username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        settingEntity = userEntity.getSetting();

        setId("mainView");

        //Header
        header = new Header(this.userService);
        header.getHorizontalBar().getInitiator().addListener(this);

        // ContentHolder for routed Views
        contentHolder.setId("contentHolder");

        //Sidebar
        sidebar = new SideBar(linkService);

        // Main Container
        HorizontalLayout mainContainer = new HorizontalLayout();
        mainContainer.add(contentHolder,sidebar);
        mainContainer.setId("mainContainer");

        VerticalLayout mainContainerVerticalLayout = new VerticalLayout();
        mainContainerVerticalLayout.add(mainContainer);
        mainContainerVerticalLayout.setId("mainContainerVerticalLayout");

        add(header, mainContainerVerticalLayout);

        //Initialize the contentHolder in the notificationPresenter
        notificationPresenter.setMainView(this);
        notificationPresenter.setEventOfNotificationViewOnSideBar();
        notificationPresenter.setCounterFromDialogToSideBar();
        notificationPresenter.getNotificationDataProvider().getInitiator().addListener(this);


    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        // The "content" is the the view you are navigating to
        // The code below sets the childWrapper to hold the view
        contentHolder.getElement().appendChild(content.getElement());
        String text = content.toString();
        //System.out.println(content);
        String color = contentSplit(text);

        //Auslesen der DB Einstellung darkmode
        //if(DB_dark == true && Lumo.DARK == false) { Lumo.DARK == true;}

        /*if(settingEntity.getDarkmode() == true ){

        }*/
        userEntity = userService.findByUsername(username);
        settingEntity = userEntity.getSetting();

        if(settingEntity.getDarkmode() == true) {
            contentHolder.getElement().getChild(0).getThemeList().add(Lumo.DARK);
            contentHolder.getElement().getChild(0).getClassList().remove("lightColorscheme");
            contentHolder.getElement().getChild(0).getClassList().add("darkColorscheme");
            setBackgroundColorDark(color);

        } else if (settingEntity.getDarkmode() == false ){
            contentHolder.getElement().getChild(0).getThemeList().remove(Lumo.DARK);
            contentHolder.getElement().getChild(0).getClassList().remove("darkColorscheme");
            contentHolder.getElement().getChild(0).getClassList().add("lightColorscheme");
            setBackgroundColor(color);
        }

    }

    public String contentSplit(String contentText){
        String[] split = contentText.split("\\.");
        int length = split.length;
        if(length > 5){
            return split[5];

        }else{
            String text = split[4];
            String[] split1 = text.split("@");
            text = split1[0];
            return text;
        }
    }

    public SideBar getSidebar() {
        return sidebar;
    }

    @Override
    public void horizontalBarClicked() {
        sidebar.setSideBarToNull();
    }

    @Override
    public void notificationCounterChanged() {
        notificationPresenter.setCounterFromDialogToSideBar();
    }

    public void setBackgroundColor(String colorName) {

        switch (colorName) {
            case "HomeView":
            case "search":
            case "myProfile":
            case "phoneBook":
            case "settings":
            case "help":
            case "InProgressView":
            case "mailing":
            case "canteen":
                setClassName("colorHome"); break;
            case "ourCompany": setClassName("colorCompany"); break;
            case "center": setClassName("colorCenter"); break;
            case "projects": setClassName("colorProjects"); break;
            case "library": setClassName("colorLibrary"); break;
            case "services": setClassName("colorServices"); break;
            case "community": setClassName("colorCommunity"); break;
            default : break;
        }
    }

    public void setBackgroundColorDark(String colorName) {

        switch (colorName) {
            case "HomeView":
            case "search":
            case "myProfile":
            case "phoneBook":
            case "settings":
            case "help":
            case "InProgressView":
            case "mailing":
            case "canteen":
                setClassName("colorDarkHome"); break;
            case "ourCompany": setClassName("colorDarkCompany"); break;
            case "center": setClassName("colorDarkCenter"); break;
            case "projects": setClassName("colorDarkProjects"); break;
            case "library": setClassName("colorDarkLibrary"); break;
            case "services": setClassName("colorDarkServices"); break;
            case "community": setClassName("colorDarkCommunity"); break;
            default : break;
        }
    }
}
