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
 * @version 1.0
 * @since   11.01.2021
 * @lastUpdated 12.01.2021
 */


@Route("login")
@PageTitle("Login | BSAG Intranet")
@CssImport("./styles/views/main/login.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm loginForm = new LoginForm();
    private UserService userService = new UserService();
    private PasswordView passwordView;
    private Image logoImage;


    public LoginView(UserService userService){
        this.userService = userService;
        setId("login");

        loginForm.setI18n(createGermanTitles());

        this.getElement().getStyle().set("background-image", "url(images/nordlicht-bremer-rathaus.jpg)");
        logoImage = new Image("images/bsag.png", "Intranetlogo");
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
     * @author  Lea Schünemann, Marieke Menna de Boer
     * @version 1.0
     * @since   11.01.2021
     * @lastUpdated 12.01.2021
     */
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
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
