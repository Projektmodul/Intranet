package com.example.application.backend.repositories;

import com.example.application.backend.entities.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * This interface is a repository for the entity setting.
 * Each entity has a repository to manage its data exchange.
 * The repository takes over the DAO functionality.
 * @author  Laura Neuendorf
 * @version 1.0
 * @since   26.01.2021
 * @lastUpdated 03.02.2021 by Laura Neuendorf
 */

public interface SettingRepository extends JpaRepository<SettingEntity, Integer> {
    SettingEntity findById(int settingsId);
}
