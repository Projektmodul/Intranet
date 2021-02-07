package com.example.application.backend.entities;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * This is a basic page class.
 *
 * @author  Sabrine Gamdou
 * @version 5.0
 * @since   05-01-2021
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */

@Entity(name = "pages")
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="page_id")
    private int pageId;

    private String title;

    @Column(name="text")
    private String content;

    private String type;

    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DocumentEntity> documents;

    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<JobOfferEntity> jobOffers;


    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ImageEntity> images;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "pages")

    private List<NewsEntity> news;

    @ManyToOne
    @JoinColumn(name ="username")
    private UserEntity user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public List<JobOfferEntity> getJobOffers() {
        return jobOffers;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }

}
