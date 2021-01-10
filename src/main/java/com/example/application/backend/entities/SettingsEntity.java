package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic setting class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="settings")
public class SettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="setting_id")
    private int settingsId;

    private String type;
    private String category;

   /* @Column(name ="font_size")
    private String fontSize;

    @Column(name ="font_type")
    private String frontType;

    @OneToOne(mappedBy = "settings")
    private UsersEntity user;*/

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

   /* public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFrontType() {
        return frontType;
    }

    public void setFrontType(String frontType) {
        this.frontType = frontType;
    }*/

}
