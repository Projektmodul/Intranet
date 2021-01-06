package com.example.application.backend.entities;

import javax.persistence.*;

/**
 * This is a basic notification class. Title, description and date are the attributes, they are
 * added to a Details component for display.
 *
 * @author  Sabrine Gamdou
 * @version 2.0
 * @since   20-12-2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="notification_id")
    private int notificationId;

    private String title;
    private String description;
    private String category;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
