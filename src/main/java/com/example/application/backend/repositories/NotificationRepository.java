package com.example.application.backend.repositories;

import com.example.application.backend.entities.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    Page<NotificationEntity> findBy(Pageable pageable);
    NotificationEntity findByNotificationId(int notificationId);
}
