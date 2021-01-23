package com.example.application.backend.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
