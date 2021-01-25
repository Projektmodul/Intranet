package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic link class.
 *
 * @author  Sabrine Gamdou
 * @version 2.0
 * @since   05.01.2020
 * @lastUpdated 25.01.2021 from Jessica Reistel, Monika Martius and Laura Neuendorf
 */

@Entity(name ="links")
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="link_id")
    private int linkId;

    private String title;
    private String url;

   /* @OneToOne(mappedBy = "link")
    private NewsEntity news;

    @ManyToOne
    @JoinColumn(name ="username")
    private UsersEntity user;*/

    /*@ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;*/

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*public NewsEntity getNews() {
        return news;
    }

    public void setNews(NewsEntity news) {
        this.news = news;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }*/
}
