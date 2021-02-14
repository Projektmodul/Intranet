package com.example.application.ui.login;

import com.example.application.backend.services.users.UserService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * This class instantiates a LoginForm component
 *
 * @author  Lea Schünemann, Marieke Menna de Boer
 * @version 2.0
 * @since   11.01.2021
 * @lastUpdated 13.02.2021
 */


@Route("login")
@PageTitle("Login | BSAG Intranet")
@CssImport("./styles/views/main/login.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm loginForm = new LoginForm();

    public LoginView(UserService userService){
        setId("login");

        loginForm.setI18n(createGermanTitles());

        this.getElement().getStyle().set("background-image", "url(images/nordlicht-bremer-rathaus.jpg)");
        Image logoImage = new Image("images/bsag.png", "Intranetlogo");
        logoImage.setId("logoImage");

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.addForgotPasswordListener(e-> new PasswordView(userService).open());

        loginForm.setAction("login");
        loginForm.setId("loginForm");

        add(loginForm, logoImage);
    }

    /**
     * With this method we want to check if there is an error query parameter present.
     *
     * @param beforeEnterEvent created before navigation happens
     */
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }

    private  LoginI18n createGermanTitles(){
        final LoginI18n loginI18n = LoginI18n.createDefault();

        loginI18n.getForm().setTitle("Login zum Intranet");
        loginI18n.getForm().setUsername("Benutzername");
        loginI18n.getForm().setPassword("Passwort");
        loginI18n.getForm().setSubmit("Einloggen");
        loginI18n.getForm().setForgotPassword("Passwort vergessen");

        return loginI18n;
    }
}
