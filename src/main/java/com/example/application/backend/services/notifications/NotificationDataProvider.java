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

    public NotificationDataProvider(){

        notificationsMap = new HashMap<>();
        checkedNotifications = new ArrayList<>();
        notificationCounter = 0;
        initiator = new NotificationCounterChangedInitiator();
        initiator.notificationCounterChanged();

    }

    /**
     * This method saves the notifications in a list and then creates notificationDetails that will be shown on
     * the UI. Each notification is rendered into a Details-component
     * @param notificationService this service manages the requests between the UI-Layer and the persistence-Layer
     */
    public void findNotification(NotificationService notificationService){
        notificationsMap.clear();

        List<NotificationEntity> notifications = notificationService.findAll();

        for (NotificationEntity entity : notifications){
            if (!entity.getStatus()) {
                fillDetails(new Details(), entity);
                notificationCounter++;
            }
        }
    }

    /**
     * This method creates a Details component from the notificationsEntities data and adds the Details to a Details-list
     * @param notificationDetails this is where the notification data will be rendered
     * @param notificationEntity is the entity to be rendered
     */
    public void fillDetails(Details notificationDetails, NotificationEntity notificationEntity){
        notificationDetails.setSummaryText(notificationEntity.getTitle());
        notificationDetails.addContent(new H5(notificationEntity.getDescription()), new Text(notificationEntity.getDate().toString()));

        notificationDetails.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.REVERSE);

        notificationsMap.put(notificationDetails, setCheckboxes(notificationEntity));
    }

    /**
     * This method adds all Details and checkboxes to the notification container with their clickEvents
     * @param allNotificationsContainer this is where all notifications details and checkboxes are stored
     * @param notificationService this service manages the requests between the UI-Layer and the persistence-Layer
     */
    public void addNotifications(VerticalLayout allNotificationsContainer, NotificationService notificationService){
        notificationsMap.forEach((details, checkbox) -> {
            checkbox.setClassName("checkbox");
            details.setId("singleNotificationDetails");
            HorizontalLayout singleNotificationContainer = new HorizontalLayout();
            singleNotificationContainer.addClassName("singleNotificationContainer");
            singleNotificationContainer.add(details, checkbox);
            addClickEventOnCheckbox(checkbox, notificationService);
            allNotificationsContainer.add(singleNotificationContainer);
        });
    }

    public void addClickEventOnCheckbox(Checkbox checkbox, NotificationService notificationService){
        checkbox.addValueChangeListener(event -> {
            if (event.getValue()){
                changeNotificationStatus(notificationService, checkbox);
            } else {
                uncheckNotification(notificationService, checkbox);
            }
        });
    }

    /**
     * This method fetches the ids of the checked notifications and compares them with the current notificationEntity
     * @param notificationEntity is the entity to be compared
     * @return int returns the id matching the id of the notificationsEntity
     */
    public int findNotificationIndex(NotificationEntity notificationEntity){
        for (int i = 0; i < checkedNotifications.size(); i++){
            if (checkedNotifications.get(i).getNotificationId() == (notificationEntity.getNotificationId())){
                return i;
            }
        }
        return -1;
    }

    public void setClickEventOnSaveButton(NativeButton saveButton, NotificationService notificationService){
        saveButton.addClickListener(e -> {
            for(NotificationEntity notification : checkedNotifications){
                notificationService.save(notification);
            }
            initiator.notificationCounterChanged();
            UI.getCurrent().getPage().reload();
        });
    }

    public void uncheckNotification(NotificationService notificationService, Checkbox checkbox){
        NotificationEntity notificationEntity = getNotification(notificationService, readIdFromCheckbox(checkbox));
        notificationEntity.setStatus(checkbox.getValue());
        checkedNotifications.remove(checkedNotifications.get(findNotificationIndex(notificationEntity)));
    }

    public void changeNotificationStatus(NotificationService notificationService, Checkbox checkbox){
        NotificationEntity notificationEntity = getNotification(notificationService, readIdFromCheckbox(checkbox));
        notificationEntity.setStatus(checkbox.getValue());
        checkedNotifications.add(notificationEntity);
    }

    public int readIdFromCheckbox(Checkbox checkbox){
        return Integer.parseInt(checkbox.getLabel());
    }

    public NotificationEntity getNotification(NotificationService notificationService, int notificationId){
        return notificationService.findById(notificationId);
    }

    /**
     * This method writes the id and the status of the notification to the checkbox
     * @param notificationEntity is the entity to be rendered
     * @return Checkbox returns the notifications Checkbox
     */
    public Checkbox setCheckboxes(NotificationEntity notificationEntity){
        return new Checkbox(Integer.toString(notificationEntity.getNotificationId()), notificationEntity.getStatus());
    }

    public int getNotificationCounter(){
        return notificationCounter;
    }

    public NotificationCounterChangedInitiator getInitiator(){
        return initiator;
    }
}
