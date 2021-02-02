package com.example.application.backend.repositories;

import com.example.application.backend.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author  Sabrine Gamdou
 * @version 2.0 
 * @since 05.01.2021 
 * @lastUpdated 01.02.2021 by Monika Martius and Jessica Reistel
 */

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRoleId(int roleId);
}
