package com.example.application.ui;

import com.example.application.ui.presenters.NotificationPresenter;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;

import java.util.Arrays;

/**
 *  MainView shows ...
 *
 *  @author Sabrine Gamdou, Anastasiya Jackwerth, Monika Martius, Vanessa Skowronsky
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/mainView.css")
@CssImport("./styles/views/main/content.css")
@PWA(name = "BSAG Intranet", shortName = "BSAG Intranet", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends VerticalLayout implements RouterLayout, HorizontalBarClickedListener {

    private VerticalLayout contentHolder = new VerticalLayout();
    private Header header;
    private  SideBar sidebar;

    //bidirectional communication between ContentHolder and NotificationPresenter
    private final NotificationPresenter notificationPresenter;

    public MainView(NotificationPresenter notificationPresenter) {
        this.notificationPresenter = notificationPresenter;

        setId("mainView");

        //Header
        header = new Header();
        header.getHorizontalBar().getInitiator().addListener(this);

        // ContentHolder for routed Views
        contentHolder.setId("contentHolder");

        //Sidebar
        sidebar = new SideBar();

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


    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        // The "content" is the the view you are navigating to
        // The code below sets the childWrapper to hold the view
        contentHolder.getElement().appendChild(content.getElement());
        String text = content.toString();
        System.out.println(content);
        String color = contentSplit(text);
        header.setBackgroundColor(color);

    }

    public String contentSplit(String contentText){
        String[] split = contentText.split("\\.");
        int length = split.length;
        if(length > 5){
            String text = split[5];
            System.out.println(text + " >5");
            return text;

        }else{
            String text = split[4];
            String[] split1 = text.split("@");
            text = split1[0];
            System.out.println(text + " <5");
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
}
