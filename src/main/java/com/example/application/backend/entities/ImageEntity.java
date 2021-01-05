package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic image class.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   05.01.2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="image_id")
    private int imageId;

    private String fileName;
    private String path;

    @OneToOne(mappedBy = "image")
    private NewsEntity news;

    @OneToOne(mappedBy = "image")
    private NoticeBoardOfferEntity noticeBoardOffer;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public NewsEntity getNews() {
        return news;
    }

    public void setNews(NewsEntity news) {
        this.news = news;
    }

    public NoticeBoardOfferEntity getNoticeBoardOffer() {
        return noticeBoardOffer;
    }

    public void setNoticeBoardOffer(NoticeBoardOfferEntity noticeBoardOffer) {
        this.noticeBoardOffer = noticeBoardOffer;
    }

}
