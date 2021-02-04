package com.example.application.ui.vertical.notifications;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ZeroNotificationsDialog extends Dialog {

    public ZeroNotificationsDialog() {
        Image zeroNotifications = new Image("images/relax.png","zeroNotificationIcon");
        zeroNotifications.setWidth("4rem");
        zeroNotifications.setHeight("auto");
        zeroNotifications.getElement().getStyle().set("align-self", "center");

        Label zeroNotificationsLabel = new Label("Jetzt ist Ruhe!");
        zeroNotificationsLabel.getElement().getStyle().set("color","#A00505");
        zeroNotificationsLabel.getElement().getStyle().set("align-self", "center");

        VerticalLayout labelIconContainer = new VerticalLayout(zeroNotificationsLabel, zeroNotifications);
        add(labelIconContainer);
    }
}
