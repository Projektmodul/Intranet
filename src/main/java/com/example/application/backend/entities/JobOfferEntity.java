package com.example.application.backend.entities;

import javax.persistence.*;

/**
 * This is a basic jobOffer class.
 *
 * @author  Sabrine Gamdou
 * @version 4.0
 * @since   05.01.2020
 * @lastUpdated 01.02.2021 by Sabrine Gamdou, Anastasiya Jackwerth
 */

@Entity(name="job_offers")
public class JobOfferEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="job_offer_id")
    private int jobOfferId;


    @Column(name ="title")
    private String title;

    private String category;

    private String type;

    private String location;

    @ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;

    @ManyToOne
    @JoinColumn(name ="username")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name="document_id")
    private DocumentEntity document;

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

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
