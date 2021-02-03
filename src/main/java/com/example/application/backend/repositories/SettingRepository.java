package com.example.application.backend.repositories;

import com.example.application.backend.entities.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<SettingEntity, Integer> {
    SettingEntity findById(int settingsId);
}
