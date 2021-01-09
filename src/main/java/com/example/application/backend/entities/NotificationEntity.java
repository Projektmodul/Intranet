package com.example.application.backend.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

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

    private boolean status;

    @Column(name ="created_at")
    private Timestamp date;

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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Only the Date is needed for display
    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
