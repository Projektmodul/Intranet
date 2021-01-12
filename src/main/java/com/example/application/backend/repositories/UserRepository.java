package com.example.application.backend.repositories;

import com.example.application.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrien Gamdou
 * @version 2.0
 * @since   04.01.2021
 * @lastUpdated 11.01.2021
 */

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //UserEntity findByUserId(int userId);
}
