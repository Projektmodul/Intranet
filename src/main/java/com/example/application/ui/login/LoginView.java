package com.example.application.ui.login;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
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
    private LoginForm login = new LoginForm();

    public LoginView(){
        setId("login");
        this.getElement().getStyle().set("background-image", "url(images/nordlicht-bremer-rathaus.jpg)");
        Image logoImage = new Image("images/bsag.png", "Intranetlogo");
        logoImage.setId("logoImage");

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");
        login.setId("loginForm");
        add(login, logoImage);
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
            login.setError(true);
        }
    }
}
