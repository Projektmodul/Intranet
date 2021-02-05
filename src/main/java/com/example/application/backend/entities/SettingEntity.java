package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic setting class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 4.0
 * @since   21-12-2020
 * @lastUpdated 05.02.2021 by Laura Neuendorf
 */

@Entity(name ="settings")
public class SettingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="setting_id")
    private int settingsId;

    private boolean darkmode;

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public boolean getDarkmode() {
        return darkmode;
    }

    public void setDarkmode(boolean darkmode) {
        this.darkmode = darkmode;
    }
}
