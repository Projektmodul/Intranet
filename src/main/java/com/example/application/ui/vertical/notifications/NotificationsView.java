package com.example.application.ui.vertical.notifications;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Notification Dialog, pops up when the Bulb/Notification icon is clicked.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   20-12-2020
 * @lastUpdated 06.01.2021
 */

@CssImport("./styles/views/main/notification.css")
public class NotificationsView extends Dialog {


    private H4 notificationText ;
    private VerticalLayout notificationsContainer;


    public NotificationsView() {




        notificationText = new H4("Notifications: ");
        notificationsContainer = new VerticalLayout(notificationText);

        this.add(notificationsContainer);
        notificationsContainer.addClassName("notificationsContainer");




        Button closeButton = new Button("Close", event -> this.close());

        closeButton.setId("notificationBtn");
        notificationsContainer.add(closeButton);
        this.setWidth("25%");



    }


    public VerticalLayout getNotificationsContainer() {
        return notificationsContainer;
    }


}
