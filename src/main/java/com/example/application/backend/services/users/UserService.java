package com.example.application.backend.services.users;


import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    public UserService(UserRepository userRepository){
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

    public UserEntity findById (int userId) {
        return getUserRepository().findByUserId(userId);
    }

    /**
     * This method loads the user by the username typed in.
     *
     * @author  Lea Schünemann, Marieke Menna de Boer
     * @version 1.0
     * @since   11.01.2021
     * @lastUpdated 12.01.2021
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity customer = userRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(customer.getUsername())
                .password(customer.getPassword())
                .authorities("USER").build();
        return user;
    }
}
