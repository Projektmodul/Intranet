package com.example.application.backend.entities;

import javax.persistence.*;
import java.util.List;


/**
 * This is a basic idea class.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   05.01.2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="ideas")
public class IdeaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idea_id")
    private int ideaId;

    private String title;
    private String description;
    private String rating;
/*
    @ManyToOne
    @JoinColumn(name ="user_id")
    private UsersEntity user;*/

    /*@ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;*/

    public IdeaEntity() {

    }

    public IdeaEntity(int ideaId, String title, String description, String rating) {
        this.ideaId = ideaId;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }


    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
/*
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }*/

}
