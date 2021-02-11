package com.example.application.backend.services.notifications;

import com.example.application.backend.entities.NotificationEntity;
import com.example.application.backend.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class for the NotificationEntities
 *
 * @author Sabrine Gamdou, Anastasiya Jackwerth
 * @version 2.0
 * @since 11.01.2021
 * @lastUpdated 11.02.2021 by Sabrine Gamdou, Anastasiya Jackwerth
 */
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public List<NotificationEntity> findAll(){
        return getNotificationRepository().findAll();
    }

    public void save(NotificationEntity notificationEntity) {
        getNotificationRepository().saveAndFlush(notificationEntity);
    }

    public NotificationEntity findById(int notificationId){
        return getNotificationRepository().findByNotificationId(notificationId);
    }
}
