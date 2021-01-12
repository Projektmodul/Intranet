package com.example.application.backend.repositories;

import com.example.application.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UserEntity findByUserId(int userId);
    UserEntity findByUsername(String username);
}
