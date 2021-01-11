package com.example.application.backend.services.users;

import com.example.application.backend.entities.AddressesEntity;
import com.example.application.backend.entities.UsersEntity;
import com.example.application.backend.repositories.AddressRepository;
import com.example.application.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void save (UsersEntity usersEntity){
        getUserRepository().saveAndFlush(usersEntity);
    }

    public UsersEntity findById (int userId) {
        return getUserRepository().findByUserId(userId);
    }
}
