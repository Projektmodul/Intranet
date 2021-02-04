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
 * @version 1.0
 * @since   03.02.2021
 * @lastUpdated
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

    public void update(SettingEntity settingEntity, ToggleButton toggleButton){
        settingEntity.setDarkmode(toggleButton.getValue());
        getSettingRepository().saveAndFlush(settingEntity);
    }

}
