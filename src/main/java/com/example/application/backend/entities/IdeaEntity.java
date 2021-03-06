package com.example.application.backend.entities;

import javax.persistence.*;

/**
 * This is a basic idea class.
 *
 * @author  Sabrine Gamdou
 * @version 3.0
 * @since   05.01.2020
 * @lastUpdated 25.01.2021 from Jessica Reistel, Litharshiga Sivarasa
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
}
