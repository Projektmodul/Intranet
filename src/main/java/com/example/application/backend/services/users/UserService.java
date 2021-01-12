package com.example.application.backend.services.users;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION
 *
 * @author  Jessica Reistel and Laura Neuendorf
 * @version 2.0
 * @since   11.01.2021
 * @lastUpdated 12.01.2021
 */

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

    public void update(UserEntity userEntity, TextField updateIban, TextArea updateJobDescription){
        userEntity.setIban(updateIban.getValue());
        userEntity.setJobDescription(updateJobDescription.getValue());
        getUserRepository().saveAndFlush(userEntity);
        UI.getCurrent().getPage().reload();
    }

}
