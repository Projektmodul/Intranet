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
 *  MainView is the subordinate view for the whole single-page-application.
 *  It contains the header, the sidebar and the content holder. The content of the content holder is routed by the
 *  mainView. MainView sets the light and dark mode, depending on the setting of the user.
 *
 *  @author Sabrine Gamdou, Anastasiya Jackwerth, Monika Martius, Vanessa Skowronsky
 *  @version 7.0
 *  @since 15.12.2020
 *  @lastUpdated 04.02.2021 by Vanessa Skowronsky and Laura Neuendorf
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/mainView.css")
@CssImport("./styles/views/main/content.css")
@PWA(name = "BSAG Intranet", shortName = "BSAG Intranet", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends VerticalLayout implements RouterLayout, HorizontalBarClickedListener, NotificationCounterChangedListener {

    private SettingEntity settingEntity;
    private final VerticalLayout contentHolder;
    private final SideBar sidebar;
    GetUserController getUserController;
    String username;

    private final NotificationPresenter notificationPresenter;

    public MainView(NotificationPresenter notificationPresenter, UserService userService, LinkService linkService) {
        setId("mainView");

        this.notificationPresenter = notificationPresenter;
        UserEntity userEntity;

        getUserController = new GetUserController();
        username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        settingEntity = userEntity.getSetting();

        Header header = new Header(userService);
        header.getHorizontalBar().getInitiator().addListener(this);

        HorizontalLayout mainContainer = new HorizontalLayout();
        mainContainer.setId("mainContainer");
        contentHolder = new VerticalLayout();
        contentHolder.setId("contentHolder");
        sidebar = new SideBar(linkService);
        mainContainer.add(contentHolder,sidebar);

        VerticalLayout mainContainerVerticalLayout = new VerticalLayout();
        mainContainerVerticalLayout.add(mainContainer);
        mainContainerVerticalLayout.setId("mainContainerVerticalLayout");

        add(header, mainContainerVerticalLayout);

        notificationPresenter.setMainView(this);
        notificationPresenter.setEventOfNotificationViewOnSideBar();
        notificationPresenter.setCounterFromDialogToSideBar();
        notificationPresenter.getNotificationDataProvider().getInitiator().addListener(this);
    }

    /**
     * The methods gets the element of the route it is navigating to and calls the methods for the background colors.
     * The method is not called by us, but from the integrated Vaadin routing system.
     * @param content Element of the content holder
     */
    @Override
    public void showRouterLayoutContent(HasElement content) {
        // The "content" is the the view you are navigating to

        contentHolder.getElement().appendChild(content.getElement());
        String text = content.toString();
        String color = contentSplit(text);

        if(settingEntity.getDarkmode()) {
            UI.getCurrent().getElement().getThemeList().add(Lumo.DARK);
            contentHolder.getElement().getChild(0).getClassList().remove("lightColorscheme");
            contentHolder.getElement().getChild(0).getClassList().add("darkColorscheme");
            setBackgroundColorDark(color);

        } else if (!settingEntity.getDarkmode() ){
            UI.getCurrent().getElement().getThemeList().remove(Lumo.DARK);
            contentHolder.getElement().getChild(0).getClassList().remove("darkColorscheme");
            contentHolder.getElement().getChild(0).getClassList().add("lightColorscheme");
            setBackgroundColor(color);
        }
    }

    /**
     * The method create a String with the name of the actual route
     * @return String
     */
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

    /**
     * The method sets the light mode background color of the whole view depending on the given parameter.
     * @param colorName Name of the color scheme of the view
     */
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

    /**
     * The method sets the dark mode background color of the whole view depending on the given parameter.
     * @param colorName Name of the color scheme of the view
     */
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
