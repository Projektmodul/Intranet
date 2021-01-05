package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic noticeBoardOffer class.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   05.01.2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="notice_board_offer")
public class NoticeBoardOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="notice_board_offer_id")
    private int noticeBoardOfferId;
    private String title;
    private String description;
    private String category;

    @OneToOne
    @JoinColumn(name="image_id")
    private ImageEntity image;

    public int getNoticeBoardOfferId() {
        return noticeBoardOfferId;
    }

    public void setNoticeBoardOfferId(int noticeBoardOfferId) {
        this.noticeBoardOfferId = noticeBoardOfferId;
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

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

}
