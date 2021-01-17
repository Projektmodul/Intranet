package com.example.application.ui.presenters;

import com.example.application.backend.services.notifications.NotificationDataProvider;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.ui.MainView;
import com.example.application.ui.vertical.notifications.NotificationsView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;


/**
 * Notification Presenter is the middleman between the Views and the Backend.
 * Role: handling the requests of the views, processing them and sending the right responses back.
 *
 * @author Sabrine Gamdou
 * @version 1.0
 * @since   06.01.2021
 * @lastUpdated 06.01.2021
 */

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NotificationPresenter {

    private MainView mainView;

    private final NotificationService notificationService;
    private final NotificationsView notificationsView;


    @Autowired
    NotificationPresenter(NotificationService notificationService){
        this.notificationService = notificationService;

        this.mainView = new MainView(this);

        notificationsView = new NotificationsView();
        NotificationDataProvider notificationDataProvider = new NotificationDataProvider();

        //The presenter handles the request of retrieving the notifications from the backend
        notificationDataProvider.findNotification(notificationService);

        //The presenter adds all retrieved notifications to the notificationsView
        notificationDataProvider.addNotifications(notificationsView.getAllNotificationsContainer(), notificationService);

    }

    //The presenter sets the clickEvent of the notification dialog
    public void setEventOfNotificationViewOnSideBar(){
        this.mainView.getSidebar().setEventOfNotificationView(this.notificationsView);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

}
