package com.example.application.backend.services.users;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void save (UserEntity userEntity){
        getUserRepository().saveAndFlush(userEntity);
    }

    public UserEntity findById (int userId) {
        System.out.println("FIND");
        UserEntity user = getUserRepository().findByUserId(userId);
        System.out.println("FIND2");
        return user;
    }

    public void update(UserEntity userEntity){
        System.out.println("TESTTTTT");
        //UserEntity userEntity = findById(1); //statt 1 muss userID abgefragt werden
        System.out.println(userEntity.getIban());
        userEntity.setIban("DE12 3456 7890");
        userEntity.setJobDescription("Ich bin eine Beschreibung.");
        System.out.println(userEntity.getIban());
        System.out.println(userEntity.getUserId());
        //usersEntity.setAddress();
        getUserRepository().saveAndFlush(userEntity);
    }

    public UserEntity getUser(int userId) {
        return this.findById(userId);
    }

}
