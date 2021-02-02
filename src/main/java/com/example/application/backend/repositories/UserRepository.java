package com.example.application.backend.repositories;

import com.example.application.backend.entities.UserEntity;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou,
 *          Lea Schünemann, Marieke Menna de Boer, Monika Martius
 * @version 4.0
 * @since   04.01.2021
 * @lastUpdated 25.01.2021
 */

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
