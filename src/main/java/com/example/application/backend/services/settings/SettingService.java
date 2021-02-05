package com.example.application.backend.services.settings;

import com.example.application.backend.entities.SettingEntity;
import com.example.application.backend.repositories.SettingRepository;
import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is service class for the entity setting.
 * The service layer processes requests from the UI layer.
 *
 * @author  Laura Neuendorf,
 * @version 2.0
 * @since   03.02.2021
 * @lastUpdated 04.02.2021 by Vanessa Skowronsky and Laura Neuendorf
 */

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    @Autowired
    public SettingService(SettingRepository settingRepository){
        this.settingRepository = settingRepository;
    }

    public SettingRepository getSettingRepository(){
        return settingRepository;
    }

    /**
     * This method updates the entered information from the toggle button in SettingView in the database
     * @param settingEntity
     * @param toggleValue
     */
    public void update(SettingEntity settingEntity, Boolean toggleValue){
        settingEntity.setDarkmode(toggleValue);
        getSettingRepository().saveAndFlush(settingEntity);
        UI.getCurrent().getPage().reload();
    }

}
