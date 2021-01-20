/*
 *  Get loggedIn username
 *
 *  @author Monika Martius
 *  @version 1.0
 *  @since 20.01.2021
 *  @lastUpdated 20.01.2021
 */

package com.example.application.backend.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String printUser(String model) {

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getUsername();
        }
    }
