package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic jobOffer class.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   05.01.2020
 * @lastUpdated 05.01.2021
 */

@Entity(name="job_offers")
public class JobOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="job_offer_id")
    private int jobOfferId;
    private String title;
    private String description;
    private String category;
    private String type;
    private String location;

    @OneToOne
    @JoinColumn(name="document_id")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private UsersEntity user;

    public int getJobOfferId() {
        return jobOfferId;
    }

    public void setJobOfferId(int jobOfferId) {
        this.jobOfferId = jobOfferId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

}
