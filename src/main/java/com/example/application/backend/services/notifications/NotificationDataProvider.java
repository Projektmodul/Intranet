package com.example.application.backend.services.notifications;

import com.example.application.backend.entities.NotificationEntity;
import com.example.application.backend.services.notifications.NotificationService;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * This class uses the service provided by the presenter to retrieve the data from the database.
 * The data is then rendered into proper GUI-components (Details in this case)
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   06.01.2021
 * @lastUpdated 06.01.2021
 */

public class NotificationDataProvider {

    private List<Details> details;

    public NotificationDataProvider(){

        details = new ArrayList<>();

    }

    public void findNotification(NotificationService notificationService){

        //Retrieve the entities from the database
        List<NotificationEntity> notifications = notificationService.findAll();

        //Each notification is rendered into a Details-component
        for(NotificationEntity entity : notifications){
            fillDetails(new Details(), entity);
        }

    }

    public void fillDetails(Details notificationDetails, NotificationEntity temp){
        //Styling the Details and adding the notification data to it
        notificationDetails.setSummaryText(temp.getTitle());
        notificationDetails.addContent(new H5(temp.getDescription()));

        notificationDetails.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.REVERSE);

        //Add the Details to the Details-list
        details.add(notificationDetails);
    }

    public void addNotifications(VerticalLayout notificationsContainer){
        //Add all Details to the notification container
        for(Details detail : details){
            notificationsContainer.add(detail);
        }
    }

}
