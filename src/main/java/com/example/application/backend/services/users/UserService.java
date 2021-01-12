package com.example.application.backend.services.users;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.UserRepository;
import com.vaadin.flow.component.UI;
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

    /*public UserEntity findById (int userId) {
        return getUserRepository().findByUserId(userId);
    }*/

    public void update(UserEntity userEntity){
        System.out.println(userEntity.getIban());
        userEntity.setIban("DE1234567890");
        userEntity.setJobDescription("Ich bin eine Beschreibung.");
        System.out.println(userEntity.getIban());
        //usersEntity.setAddress();
        getUserRepository().saveAndFlush(userEntity);
        UI.getCurrent().getPage().reload();
    }

}
