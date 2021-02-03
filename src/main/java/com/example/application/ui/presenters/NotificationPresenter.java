package com.example.application.ui.presenters;

import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.notifications.NotificationDataProvider;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.users.UserService;
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
 * @version 2.0
 * @since   06.01.2021
 * @lastUpdated 01.02.2021 by Jessica Reistel
 */

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NotificationPresenter {

    private MainView mainView;

    private final NotificationService notificationService;
    private final UserService userService;
    private final LinkService linkService;
    private final NotificationsView notificationsView;
    private NotificationDataProvider notificationDataProvider;


    @Autowired
    NotificationPresenter(NotificationService notificationService, UserService userService, LinkService linkService) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.linkService = linkService;

        notificationsView = new NotificationsView();

        notificationDataProvider = new NotificationDataProvider();

        //The presenter handles the request of retrieving the notifications from the backend
        notificationDataProvider.findNotification(notificationService);

        //The presenter adds all retrieved notifications to the notificationsView
        notificationDataProvider.addNotifications(notificationsView.getAllNotificationsContainer(), notificationService);

        notificationDataProvider.setClickEventOnSaveButton(notificationsView.getDeleteButton(), notificationService);

        notificationsView.setNotificationsCounter(notificationDataProvider.getNotificationCounter());

        this.mainView = new MainView(this, this.userService, this.linkService);
        this.mainView.getSidebar().setNotificationCounter(notificationDataProvider.getNotificationCounter());
    }

    //The presenter sets the clickEvent of the notification dialog
    public void setEventOfNotificationViewOnSideBar() {
        this.mainView.getSidebar().setEventOfNotificationView(this.notificationsView);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void setCounterFromDialogToSideBar(){
        this.mainView.getSidebar().setNotificationCounter(notificationDataProvider.getNotificationCounter());
        this.mainView.getSidebar().setCounterSpan(Integer.toString(this.mainView.getSidebar().getNotificationCounter()));
    }

    public NotificationDataProvider getNotificationDataProvider() {
        return notificationDataProvider;
    }
}
