package com.example.application.ui.vertical.notifications;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Notification Dialog, pops up when the Bell/Notification icon is clicked.
 *
 * @author  Sabrine Gamdou
 * @version 2.0
 * @since   20.12.2020
 * @lastUpdated 26.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */
public class NotificationsView extends Dialog {

    private final VerticalLayout allNotificationsContainer;
    private final NativeButton deleteButton;

    private int notificationsCounter;

    public NotificationsView() {
        setId("notifications");

        H4 notificationText = new H4("Ihre Benachrichtigungen: ");

        allNotificationsContainer = new VerticalLayout(notificationText);

        deleteButton = new NativeButton("Löschen");
        deleteButton.setId("deleteButton");
        allNotificationsContainer.add(deleteButton);

        this.add(allNotificationsContainer);
        allNotificationsContainer.addClassName("notificationsContainer");

        Button closeButton = new Button("Schließen", event -> this.close());

        closeButton.setId("notificationBtn");
        allNotificationsContainer.add(closeButton);
        this.setWidth("30%");
    }

    public VerticalLayout getAllNotificationsContainer() {
        return allNotificationsContainer;
    }

    public NativeButton getDeleteButton() {
        return deleteButton;
    }

    public void setNotificationsCounter(int notificationsCounter) {
        this.notificationsCounter = notificationsCounter;
    }
}
