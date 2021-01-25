package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic image class.
 *
 * @author  Sabrine Gamdou, Jessica Reistel, Monika Martius and Laura Neuendorf
 * @version 2.0
 * @since   05.01.2020
 * @lastUpdated 25.01.2021
 */

@Entity(name ="images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="image_id")
    private int imageId;

    @Column(name ="file_name")
    private String fileName;
    private String path;

   /* @OneToOne(mappedBy = "image")
    private NewsEntity news;

    @OneToOne(mappedBy = "image")
    private NoticeBoardOfferEntity noticeBoardOffer;
*/
    @ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;

    @ManyToOne
    @JoinColumn(name ="username")
    private UserEntity user;

    public ImageEntity(){

    }

    public ImageEntity(String fileName, String path, PageEntity page, UserEntity user) {

        this.fileName = fileName;
        this.path = path;
        this.page = page;
        this.user = user;
    }

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

  /*  public NewsEntity getNews() {
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
*/
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

   public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }


}
