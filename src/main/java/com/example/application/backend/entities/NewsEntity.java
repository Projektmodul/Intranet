package com.example.application.backend.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * This is a basic news class.
 *
 * @author  Sabrine Gamdou
 * @version 4.0
 * @since   05.01.2020
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */

@Entity(name ="news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="news_id")
    private int newsId;
    private String title;
    private String description;
    private String text;
    private String type;

    @Column(name ="created_at")
    private Date date;

    @OneToOne
    @JoinColumn(name="image_id")
    private ImageEntity image;



    @ManyToMany
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable( //this defines the relationship and the foreign key columns
            name = "pages_news",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "page_id")
    )
    private List<PageEntity> pages;



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

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
    public void setDate() {
        this.date = new Date(System.currentTimeMillis());
    }

    public List<PageEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageEntity> pages) {
        this.pages = pages;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
