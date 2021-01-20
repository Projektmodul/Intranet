package com.example.application.ui;

import com.example.application.backend.controller.LoginController;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.users.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

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

    public HomeView(UserService userService, LoginController loginController) {
        this.userService = userService;
        this.loginController = loginController;
        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        String user = loginController.printUser("username");
        userEntity = userService.findByUsername(user);

        add(new H1("Herzlich Willkommen " + userEntity.getFirstName() + " " + userEntity.getSurname() ));
    }
}