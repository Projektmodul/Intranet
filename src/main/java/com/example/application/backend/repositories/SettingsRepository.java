package com.example.application.backend.repositories;

import com.example.application.backend.entities.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<SettingsEntity, Integer> {
}
