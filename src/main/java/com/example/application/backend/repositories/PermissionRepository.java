package com.example.application.backend.repositories;

import com.example.application.backend.entities.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionsEntity, Integer> {
}
