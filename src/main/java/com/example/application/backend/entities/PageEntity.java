package com.example.application.backend.entities;


import javax.persistence.*;
import java.util.List;

/**
 * This is a basic page class.
 *
 * @author  Sabrine Gamdou
 * @version 2.0
 * @since   05-01-2021
 * @lastUpdated 19.01.2021
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
/*
    @OneToMany(mappedBy = "page")
    private List<LinkEntity> links;
*/
    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    private List<DocumentEntity> documents;

/*

    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    private List<ImageEntity> images;
*/

  /*  @OneToMany(mappedBy = "page")
    private List<IdeaEntity> ideas;*/
/*
    @OneToMany(mappedBy = "page")
    private List<NoticeBoardOfferEntity> noticeBoardOffers;

    @ManyToMany
    @JoinTable( //this defines the relationship and the foreign key columns
            name = "pages_news",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id")
    )
    private List<NewsEntity> news;*/

    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserEntity user;


    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

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

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    /*public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }*/
  /*  public List<LinkEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkEntity> links) {
        this.links = links;
    }





    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }





    public List<IdeaEntity> getIdeas() { return ideas; }

    public void setIdeas(List<IdeaEntity> ideas) {
        this.ideas = ideas;
    }
*/
}
