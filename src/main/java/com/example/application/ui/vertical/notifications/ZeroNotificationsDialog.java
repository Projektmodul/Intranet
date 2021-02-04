package com.example.application.ui.vertical.notifications;

import com.vaadin.flow.component.dialog.Dialog;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * This dialog is shown when no notifications are available/not read by the user.
 *
 * @author Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since   04.02.2021
 * @lastUpdated 04.02.2021 by Sabrine Gamdou
 */
public class ZeroNotificationsDialog extends Dialog {

    public ZeroNotificationsDialog() {
        Icon zeroNotifications = new Icon(VaadinIcon.GOLF);
        zeroNotifications.setSize("4rem");
        zeroNotifications.getElement().getStyle().set("align-self", "center");

        Label zeroNotificationsLabel = new Label("Sie haben momentan keine neuen Benachrichtigungen!");
        zeroNotificationsLabel.getElement().getStyle().set("color","#A00505");
        zeroNotificationsLabel.getElement().getStyle().set("align-self", "center");

        VerticalLayout labelIconContainer = new VerticalLayout(zeroNotificationsLabel, zeroNotifications);
        add(labelIconContainer);
    }
}
