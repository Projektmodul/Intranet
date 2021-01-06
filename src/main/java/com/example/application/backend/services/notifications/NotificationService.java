package com.example.application.backend.services.notifications;

import com.example.application.backend.entities.NotificationEntity;
import com.example.application.backend.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<NotificationEntity> find(Pageable pageable) {
        return getNotificationRepository().findBy(pageable);
    }

}
