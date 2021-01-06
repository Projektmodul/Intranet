package com.example.application.ui;

import com.example.application.ui.presenters.NotificationPresenter;
import com.vaadin.flow.component.dependency.CssImport;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;

@ParentLayout(MainView.class)
@CssImport("./styles/views/main/mainView.css")
public class ContentHolder extends HorizontalLayout implements RouterLayout {

    //bidirectional communication between ContentHolder and NotificationPresenter
    private final NotificationPresenter notificationPresenter;

    private final SideBar sideBar;


    public ContentHolder(NotificationPresenter notificationPresenter) {

        setId("contentHolder");
        this.sideBar = new SideBar();
        add(sideBar);

        this.notificationPresenter = notificationPresenter;

        //Initialize the contentHolder in the notificationPresenter
        notificationPresenter.setContentHolder(this);
        notificationPresenter.setEventOfNotificationViewOnSideBar();

    }

    public SideBar getSideBar() {
        return sideBar;
    }
}
