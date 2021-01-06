package com.example.application.ui.vertical.notifications;

import com.example.application.backend.entities.NotificationEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Notification Dialog, pops up when the Bulb/Notification icon is clicked.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   20-12-2020
 */

@CssImport("./styles/views/main/notification.css")
public class NotificationsView extends Dialog {

    private H4 notificationText ;
    /*private VerticalLayout notificationsContainer;
    private NotificationEntity[] notifications;

    public NotificationsView(){
        //These are only for demo, could be deleted once the data in the database is ready to be used
        NotificationEntity not1 = new NotificationEntity("Es wurde eine neue Datei hinzugefügt",
                "Mauris at mi vel felis commodo facilisis. Morbi mattis erat lorem, " +
                        "in cursus mauris consectetur vitae. Interdum et malesuada fames ac ante ipsum primis in faucibus.", "10.07.2022");
        NotificationEntity not2 = new NotificationEntity("Die Speisekarte für diese Woche ist online",
                "Mauris at mi vel felis commodo facilisis. Morbi mattis erat lorem, " +
                        "in cursus mauris consectetur vitae. Interdum et malesuada fames ac ante ipsum primis in faucibus.", "23.12.2022");
        NotificationEntity not3 = new NotificationEntity("Holen Sie sich die lokalen Nachrichten!",
                "Mauris at mi vel felis commodo facilisis. Morbi mattis erat lorem, " +
                        "in cursus mauris consectetur vitae. Interdum et malesuada fames ac ante ipsum primis in faucibus.", "08.03.2022");

        notifications = new NotificationEntity[]{not1,not2,not3};

        notificationText = new H4("Notifications: ");
        notificationsContainer = new VerticalLayout(notificationText);

        this.add(notificationsContainer);
        notificationsContainer.addClassName("notificationsContainer");

        Span message = new Span();

        Button closeButton = new Button("Close", event -> {
            message.setText("Closed...");
            this.close();
        });

        closeButton.setId("notificationBtn");
        notificationsContainer.add(closeButton);
        this.setWidth("25%");
    }

    public void addNotifications(){
        for(NotificationEntity notificationEntity : notifications){
            notificationsContainer.add(notificationEntity.getDetails());
        }
    }*/

}
