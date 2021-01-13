package com.example.application.backend.repositories;

import com.example.application.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou,
            Lea Schünemann, Marieke Menna de Boer
 * @version 3.0
 * @since   04.01.2021
 * @lastUpdated 13.01.2021
 */
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UserEntity findByUserId(int userId);
    UserEntity findByUsername(String username);
}
