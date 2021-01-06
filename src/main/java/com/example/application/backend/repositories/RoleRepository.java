package com.example.application.backend.repositories;

import com.example.application.backend.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RolesEntity, Integer> {
}
