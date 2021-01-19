package com.example.application.ui;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.users.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *  Home View shows ...
 *
 *  @author Vanessa Skowronsky, Jessica Reistel, Laura Neuendorf
 *  @version 3.0
 *  @since 04.01.2021
 *  @lastUpdated 18.01.2021
 */
@RouteAlias(value = "", layout = MainView.class)
@Route(value = "home", layout = MainView.class)
@PageTitle("BSAG Intranet")

public class HomeView extends Div {

    private UserService userService;
    private UserEntity userEntity;

    public HomeView(UserService userService) {

        this.userService = userService;


        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        userEntity = userService.findById(1);

        add(new H1("Herzlich Willkommen " + userEntity.getFirstName() + " " + userEntity.getSurname() + " !"));
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

}