package com.example.application.backend.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * This is a basic notification class. Title, description and date are the attributes, they are
 * added to a Details component for display.
 *
 * @author  Sabrine Gamdou
 * @version 3.0
 * @since   20-12-2020
 * @lastUpdated 25.01.2021 from Jessica Reistel, Monika Martius and Laura Neuendorf
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

    @OneToOne(mappedBy = "notification", fetch = FetchType.LAZY)
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name ="username")
    private UserEntity user;

    public NotificationEntity(String title, String description, String category, boolean status, UserEntity user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.user = user;
    }

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

    /**
     * Only the Date is needed for display
     * @return new Date(date.getTime())
     */
    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
