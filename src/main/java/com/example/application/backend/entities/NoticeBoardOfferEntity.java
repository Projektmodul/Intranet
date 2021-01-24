package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic noticeBoardOffer class.
 *
 * @author  Sabrine Gamdou
 * @version 2.0
 * @since   05.01.2020
 * @lastUpdated 25.01.2021 from Jessica Reistel, Monika Martius and Laura Neuendorf
 */

@Entity(name ="notice_board_offers")
public class NoticeBoardOfferEntity implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="notice_board_offer_id")
    private int noticeBoardOfferId;
    private String title;
    private String description;
    private String category;
    private String price;

   /* @OneToOne
    @JoinColumn(name="image_id")
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name ="username")
    private UsersEntity user;*/
/*
    @ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;*/

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

    public String getPrice() {return price;}

    public void setPrice(String price) { this.price = price; }
/*
    public int getImageId() { return imageId; }

    public void setImageId(int imageId) { this.imageId = imageId; }

     public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;

    @Override
    public NoticeBoardOfferEntity clone() {
        try {
                return (NoticeBoardOfferEntity)super.clone();
        } catch (CloneNotSupportedException e) {
                return null;
            }
    }  }*/

    @Override
    public int hashCode() {
        return noticeBoardOfferId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof NoticeBoardOfferEntity)) {
            return false;
        }
        NoticeBoardOfferEntity other = (NoticeBoardOfferEntity) obj;
        return noticeBoardOfferId == other.noticeBoardOfferId;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public NoticeBoardOfferEntity clone() { //NOSONAR
        try {
            return (NoticeBoardOfferEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(
                    "The Person object could not be cloned.", e);
        }
    }
}
