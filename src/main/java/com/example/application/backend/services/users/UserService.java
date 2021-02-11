package com.example.application.backend.services.users;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * This class is service class for the entity user.
 * The service layer processes requests from the UI layer.
 *
 * @author  Jessica Reistel and Laura Neuendorf,
 *          Lea Schünemann, Marieke Menna de Boer, Monika Martius
 * @version 5.0
 * @since   11.01.2021
 * @lastUpdated 02.02.2021
 */

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public UserService(){}

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void save (UserEntity userEntity){
        getUserRepository().saveAndFlush(userEntity);
    }

    public UserEntity findByUsername (String username) {return getUserRepository().findByUsername(username);}

    /**
     * This method updates the entered information from the MyProfilView in the database
     * @param userEntity is the entity to be compared
     * @param updateIban is a text field where the new IBAN is set
     * @param updateJobDescription is a text area where the new job description is set
     */
    public void update(UserEntity userEntity, TextField updateIban, TextArea updateJobDescription){
        userEntity.setIban(updateIban.getValue());
        userEntity.setJobDescription(updateJobDescription.getValue());
        getUserRepository().saveAndFlush(userEntity);
        UI.getCurrent().getPage().reload();
    }

    /**
     * This method updates the password from user
     * @param userEntity is the entity to be compared
     * @param password is a string where the new password is set
     */
    public void passwordUpdate(UserEntity userEntity, String password){
        userEntity.setPassword(password);
        getUserRepository().saveAndFlush(userEntity);
    }

    /**
     * This method loads the user by the username typed in.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity customer = userRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(customer.getUsername())
                .password(customer.getPassword())
                .authorities("USER").build();

    }
}