package com.example.application.ui.login;

import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.users.UserService;
import com.vaadin.flow.component.Key;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.validator.RegexpValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * This class instantiates a PasswordView component
 * which allows the user to change their password.
 *
 * @author  Lea Schünemann, Marieke Menna de Boer
 * @version 1.0
 * @since   18.01.2021
 * @lastUpdated 02.02.2021
 */

@Route("changePassword")
@PageTitle("changePassword | BSAG")
public class PasswordView extends Dialog {

    TextField username = new TextField("Benutzername:");
    TextField securityAnswer = new TextField("Antwort:");
    Label securityQuestion = new Label ("Sicherheitsfrage: ");
    Label securityQuestionUser = new Label("");

    Button commitUserButton = new Button("OK");
    Button commitButton = new Button("OK");
    Button confirm = new Button("Annehmen");
    Button cancel = new Button("Abbrechen");

    PasswordField password2 = new PasswordField();
    PasswordField password = new PasswordField();

    Binder<UserEntity> binder = new BeanValidationBinder<>(UserEntity.class);

    private UserService userService = new UserService();
    private UserEntity userEntity = new UserEntity();

    private String answerUser = "";
    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";

    public PasswordView(UserService userService){

        setId("passwordChange");
        this.userService = userService;

        password.setLabel("Neues Passwort eingeben:");
        password.setPlaceholder("Passwort");

        password2.setLabel("Neues Passwort wiederholen: ");
        password2.setPlaceholder("Passwort");

        password.setAutoselect(true);
        password2.setAutoselect(true);

        commitButton.setEnabled(false);
        confirm.setEnabled(false);

        VerticalLayout layout = new VerticalLayout(username, commitUserButton);

        binder.forField(username).withValidator((s, valueContext) -> {
            if(userService.findByUsername(username.getValue()) == null) {
                    return ValidationResult.error("Benutzername konnte nicht gefunden werden.");
            }else{
                    return ValidationResult.ok();
            }
        }).bind(UserEntity::getUsername, UserEntity::setUsername);
        binder.setBean(userEntity);

        commitUserButton.addClickShortcut(Key.ENTER);
        commitUserButton.addClickListener(click ->{
            userEntity = userService.findByUsername(username.getValue());
            if(userEntity != null) {
                answerUser = userEntity.getSecurityAnswer();
                securityQuestionUser = new Label (userEntity.getSecurityQuestion());

                commitUserButton.setEnabled(false);
                commitButton.setEnabled(true);
                layout.add(securityQuestion, securityQuestionUser);
                layout.add(securityAnswer, commitButton);
            }
        });

        binder.forField(securityAnswer).withValidator((s, valueContext) -> {
                if (!answerUser.equals(securityAnswer.getValue())) {
                    return ValidationResult.error("Die Antwort ist nicht korrekt.");
                }else{
                    return ValidationResult.ok();
                }
        }).bind(UserEntity::getSecurityAnswer, UserEntity::setSecurityAnswer);
        binder.setBean(userEntity);

        commitButton.addClickShortcut(Key.ENTER);
        commitButton.addClickListener(click -> {
            if(securityAnswer.getValue().equals(userEntity.getSecurityAnswer())){
                commitButton.setEnabled(false);
                confirm.setEnabled(true);
                layout.add(new Label("Bitte geben Sie Ihr neues Passwort ein"), password, password2);
            }
        });

        binder.forField(password).withValidator(new RegexpValidator("Das Passwort muss mindestens aus 8 Zeichen, einem Großbuchstaben, einem Kleinbuchstaben und einem Sonderzeichen (!@#$%^&+=) bestehen.", passwordPattern)).bind(UserEntity::getPassword, UserEntity::setPassword);
        binder.setBean(userEntity);

        binder.forField(password2).withValidator((s, valueContext) -> {
            if (!password2.getValue().equals(password.getValue())) {

                return ValidationResult.error("Passwörter stimmen nicht überein.");
            } else {
                return ValidationResult.ok();
            }
        }).bind(UserEntity::getPassword, UserEntity::setPassword);
        binder.setBean(userEntity);

        confirm.addClickShortcut(Key.ENTER);
        confirm.addClickListener(e ->{
            if(password.getValue().equals(password2.getValue()) && password.getValue().matches(passwordPattern)){
                String hashedPassword = passwordEncoder().encode(password.getValue());
                userService.passwordUpdate(userEntity, hashedPassword);
                close();
            }
        });

        cancel.addClickListener(e -> close());

        add(layout, cancel, confirm);
    }

    /**
     * This method encrypts the typed in password in 11 rounds (strength).
     *
     * @return BCryptPasswordEncoder
     */
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}