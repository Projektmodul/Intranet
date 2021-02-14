package com.example.application.backend.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class returns the current logged in user from Spring Security
 *
 * @author  Monika Martius
 * @version 1.0
 * @since   23.01.2021
 * @lastUpdated 23.01.2021
 */
public class GetUserController {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public String getUsername(){
        String username;
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }
        else{
            username = principal.toString();
        }
        return username;
    }
}