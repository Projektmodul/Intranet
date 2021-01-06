package com.example.application.backend.entities;

import javax.persistence.*;
import java.util.List;


/**
 * This is a basic news class.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   05.01.2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="news_id")
    private int newsId;
    private String title;
    private String description;
    private String type;

    @OneToOne
    @JoinColumn(name="link_id")
    private LinkEntity link;

    @OneToOne
    @JoinColumn(name="image_id")
    private ImageEntity image;

    @ManyToMany(mappedBy = "news")
    private List<PageEntity> pages;

    @OneToOne
    @JoinColumn(name="notification_id")
    private NotificationEntity notification;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkEntity getLink() {
        return link;
    }

    public void setLink(LinkEntity link) {
        this.link = link;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public List<PageEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageEntity> pages) {
        this.pages = pages;
    }

    public NotificationEntity getNotification() {
        return notification;
    }

    public void setNotification(NotificationEntity notification) {
        this.notification = notification;
    }


}
