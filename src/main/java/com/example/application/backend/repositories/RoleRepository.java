package com.example.application.backend.repositories;

import com.example.application.backend.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author  Monika Martius and Jessica Reistel
 * @version 1.0
 * @since   01.02.2021
 */

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRoleId(int roleId);
}
