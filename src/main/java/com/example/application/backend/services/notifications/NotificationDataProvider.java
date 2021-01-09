package com.example.application.backend.services.notifications;

import com.example.application.backend.entities.NotificationEntity;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class uses the service provided by the presenter to retrieve the data from the database.
 * The data is then rendered into proper GUI-components (Details in this case and a checkbox)
 *
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   06.01.2021
 * @lastUpdated 09.01.2021
 */


public class NotificationDataProvider {

    Map<Details, Checkbox> notificationsMap;

    public NotificationDataProvider(){

        notificationsMap = new HashMap<>();

    }

    public void findNotification(NotificationService notificationService){
        notificationsMap.clear();
        //Retrieve the entities from the database
        List<NotificationEntity> notifications = notificationService.findAll();

        //Each notification is rendered into a Details-component
        for(NotificationEntity entity : notifications){
            if(!entity.getStatus()) fillDetails(new Details(), entity);
        }
    }

    public void fillDetails(Details notificationDetails, NotificationEntity notificationEntity){
        //Styling the Details and adding the notification data to it
        notificationDetails.setSummaryText(notificationEntity.getTitle());
        notificationDetails.addContent(new H5(notificationEntity.getDescription()),new Text(notificationEntity.getDate().toString()));

        notificationDetails.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.REVERSE);

        //Add the Details to the Details-list
        notificationsMap.put(notificationDetails, setCheckboxes(notificationEntity));

    }

    public void addNotifications(VerticalLayout allNotificationsContainer, NotificationService notificationService){
        //Add all Details and checkboxes to the notification container with their clickEvents
        //Set styling classes for the layout and the checkboxes
        notificationsMap.forEach( (details, checkbox) -> {
            checkbox.setClassName("checkbox");
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.addClassName("layout");
            horizontalLayout.add(checkbox, details);
            addClickEventOnCheckbox(checkbox, horizontalLayout, notificationService);
            allNotificationsContainer.add(horizontalLayout);
        });
    }

    public void addClickEventOnCheckbox(Checkbox checkbox, HorizontalLayout layout, NotificationService notificationService){
        checkbox.addValueChangeListener(event -> {
            if (event.getValue()) {
                //Once the checkbox is clicked => true, the notifications will disappear from the dialog
                //An animation could be added here to make the process more user friendly
                layout.setVisible(false);
                changeNotificationStatus(notificationService, checkbox);
            }
        });
    }

    public void changeNotificationStatus(NotificationService notificationService, Checkbox checkbox){
        NotificationEntity notificationEntity = getNotification(notificationService,readIdFromCheckbox(checkbox));
        notificationEntity.setStatus(checkbox.getValue());
        notificationService.save(notificationEntity);
    }

    public int readIdFromCheckbox(Checkbox checkbox){
        return Integer.parseInt(checkbox.getLabel());
    }

    public NotificationEntity getNotification(NotificationService notificationService, int notificationId){
        return notificationService.findById(notificationId);
    }

    public Checkbox setCheckboxes(NotificationEntity notificationEntity){
        //write the id and the status of the notification to the checkbox
        return new Checkbox(Integer.toString(notificationEntity.getNotificationId()),notificationEntity.getStatus());
    }

}
