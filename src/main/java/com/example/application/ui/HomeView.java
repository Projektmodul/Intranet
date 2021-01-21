package com.example.application.ui;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.vaadin.flow.component.html.*;
import com.example.application.backend.controller.LoginController;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.users.UserService;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *  Home View shows ...
 *
 *  @author Vanessa Skowronsky, Monika Martius, Laura Neuendorf, Jessica Reistel
 *  @version 3.0
 *  @since 04.01.2021
 *  @lastUpdated 20.01.2021
 */
@RouteAlias(value = "", layout = MainView.class)
@Route(value = "home", layout = MainView.class)
@PageTitle("BSAG Intranet")

public class HomeView extends Div {

    private UserService userService;
    private UserEntity userEntity;
    LoginController loginController;
    private PageService pageService;
    private H1 pageTitle;
    private PageEntity pageEntity;

    public HomeView(UserService userService, LoginController loginController, PageService pageService) {

        this.userService = userService;
        this.loginController = loginController;
        this.pageService = pageService;

        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        setData();
    }

    private void setData(){
        String user = loginController.printUser("username");
        userEntity = userService.findByUsername(user);

        PageEntity pageEntity = pageService.findPageById(1);
        pageTitle = new H1(pageEntity.getTitle() + " " + userEntity.getFirstName() +" " + userEntity.getSurname() );
        this.add(pageTitle);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

}