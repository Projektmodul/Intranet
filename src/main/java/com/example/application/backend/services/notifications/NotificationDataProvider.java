package com.example.application.backend.services.notifications;

import com.example.application.backend.entities.NotificationEntity;
import com.example.application.ui.vertical.notifications.NotificationCounterChangedInitiator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class uses the service provided by the presenter to retrieve the data from the database.
 * The data is then rendered into proper GUI-components (Details in this case and a checkbox)
 *
 *
 * @author Sabrine Gamdou
 * @version 2.0
 * @since 06.01.2021
 * @lastUpdated 26.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */


public class NotificationDataProvider {

    private Map<Details, Checkbox> notificationsMap;
    private List<NotificationEntity> checkedNotifications;

    private NotificationCounterChangedInitiator initiator;

    private int notificationCounter;

    public NotificationDataProvider() {

        notificationsMap = new HashMap<>();
        checkedNotifications = new ArrayList<>();
        notificationCounter = 0;
        initiator = new NotificationCounterChangedInitiator();
        initiator.notificationCounterChanged();

    }

    public void findNotification(NotificationService notificationService) {
        notificationsMap.clear();
        //Retrieve the entities from the database
        List<NotificationEntity> notifications = notificationService.findAll();

        //Each notification is rendered into a Details-component
        for (NotificationEntity entity : notifications) {
            if (!entity.getStatus()) {
                fillDetails(new Details(), entity);
                notificationCounter++;
            }
        }
    }

    public void fillDetails(Details notificationDetails, NotificationEntity notificationEntity) {
        //Styling the Details and adding the notification data to it
        notificationDetails.setSummaryText(notificationEntity.getTitle());
        notificationDetails.addContent(new H5(notificationEntity.getDescription()), new Text(notificationEntity.getDate().toString()));

        notificationDetails.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.REVERSE);

        //Add the Details to the Details-list
        notificationsMap.put(notificationDetails, setCheckboxes(notificationEntity));

    }

    public void addNotifications(VerticalLayout allNotificationsContainer, NotificationService notificationService) {
        //Add all Details and checkboxes to the notification container with their clickEvents
        //Set styling classes for the layout and the checkboxes
        notificationsMap.forEach((details, checkbox) -> {
            checkbox.setClassName("checkbox");
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.addClassName("layout");
            horizontalLayout.add(checkbox, details);
            addClickEventOnCheckbox(checkbox, horizontalLayout, notificationService);
            allNotificationsContainer.add(horizontalLayout);
        });
    }

    public void addClickEventOnCheckbox(Checkbox checkbox, HorizontalLayout layout, NotificationService notificationService) {
        checkbox.addValueChangeListener(event -> {
            if (event.getValue()) {

                changeNotificationStatus(notificationService, checkbox);
            } else {
                uncheckNotification(notificationService, checkbox);
            }
        });
    }

    public int findNotificationIndex(NotificationEntity notificationEntity) {
        for (int i = 0; i < checkedNotifications.size(); i++) {
            if (checkedNotifications.get(i).getNotificationId() == (notificationEntity.getNotificationId())) {
                return i;
            }
        }
        return -1;
    }

    public void setClickEventOnSaveButton(NativeButton saveButton, NotificationService notificationService) {
        saveButton.addClickListener(e -> {
            for(NotificationEntity notification : checkedNotifications) {
                notificationService.save(notification);
            }
            initiator.notificationCounterChanged();
            UI.getCurrent().getPage().reload();
        });
    }

    public void uncheckNotification(NotificationService notificationService, Checkbox checkbox) {
        NotificationEntity notificationEntity = getNotification(notificationService, readIdFromCheckbox(checkbox));
        notificationEntity.setStatus(checkbox.getValue());
        checkedNotifications.remove(checkedNotifications.get(findNotificationIndex(notificationEntity)));
    }

    public void changeNotificationStatus(NotificationService notificationService, Checkbox checkbox) {
        NotificationEntity notificationEntity = getNotification(notificationService, readIdFromCheckbox(checkbox));
        notificationEntity.setStatus(checkbox.getValue());
        checkedNotifications.add(notificationEntity);
    }

    public int readIdFromCheckbox(Checkbox checkbox) {
        return Integer.parseInt(checkbox.getLabel());
    }

    public NotificationEntity getNotification(NotificationService notificationService, int notificationId) {
        return notificationService.findById(notificationId);
    }

    public Checkbox setCheckboxes(NotificationEntity notificationEntity) {
        //write the id and the status of the notification to the checkbox
        return new Checkbox(Integer.toString(notificationEntity.getNotificationId()), notificationEntity.getStatus());
    }

    public int getNotificationCounter() {
        return notificationCounter;
    }

    public NotificationCounterChangedInitiator getInitiator() {
        return initiator;
    }
}
