package com.example.application.ui.vertical.myProfile;

import com.example.application.backend.entity.Users;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

import javax.swing.text.html.parser.ContentModel;

@Route(value = "myProfile", layout = MainView.class)
@PageTitle("Mein Profil")
public class MyProfileView extends Div {

    public MyProfileView() {
        setId("myProfile-view");

        Users user = new Users(1, "firstname", "surname", 3, "DE1273462537274", 'I', 5, 556434, "email", "wir sind toll" );


        VerticalLayout content = new VerticalLayout();
        content.addComponentAsFirst(new Label("Mein Profil"));

        HorizontalLayout data = new HorizontalLayout();

        VerticalLayout left = new VerticalLayout();

        TextField firstname = new TextField();
        firstname.setValue(user.getFirstname());
        firstname.setLabel("Vorname");
        firstname.setReadOnly(true);

        TextField surname = new TextField();
        surname.setValue(user.getSurname());
        surname.setLabel("Nachname");
        surname.setReadOnly(true);

        HorizontalLayout name = new HorizontalLayout();
        name.addComponentAsFirst(firstname);
        name.addComponentAtIndex(1, surname);
        left.addComponentAsFirst(name);

        TextField email = new TextField();
        email.setValue(user.getEmail());
        email.setLabel("E-Mail");
        email.setReadOnly(true);

        TextField telephone = new TextField();
        telephone.setValue(user.getTelefon());
        telephone.setLabel("Telefonnummer");
        telephone.setReadOnly(true);

        HorizontalLayout emailPhone = new HorizontalLayout();
        emailPhone.addComponentAsFirst(email);
        emailPhone.addComponentAtIndex(1, telephone);
        left.addComponentAtIndex(1, emailPhone);

        TextField center = new TextField();
        center.setValue(user.getCenter());
        center.setLabel("Center");
        center.setReadOnly(true);

        TextField roomnumber = new TextField();
        roomnumber.setValue(user.getRoomnumber());
        roomnumber.setLabel("Raumnummer");
        roomnumber.setReadOnly(true);

        HorizontalLayout centerRoom = new HorizontalLayout();
        centerRoom.addComponentAsFirst(center);
        centerRoom.addComponentAtIndex(1, roomnumber);
        left.addComponentAtIndex(2, centerRoom);

        TextField address = new TextField();
        address.setValue(user.getAddress_id());
        address.setLabel("Adresse");
        address.setReadOnly(true);

        left.addComponentAtIndex(3, address);

        TextField iban = new TextField();
        iban.setValue(user.getIban());
        iban.setLabel("Kontodaten");
        iban.setReadOnly(true);

        left.addComponentAtIndex(4, iban);

        VerticalLayout right = new VerticalLayout();

        Image profilepicture = new Image("images/user.png", "My Profile Picture");
        profilepicture.setHeight("auto");
        profilepicture.setWidth("15rem");
        profilepicture.addClassName("user");

        right.addComponentAsFirst(profilepicture);

        TextField job_description = new TextField();
        job_description.setValue(user.getJob_description());
        job_description.setLabel("Tätigkeitsbeschreibung");
        job_description.setReadOnly(true);

        right.addComponentAtIndex(1, job_description);

        Button updateProfile = new Button("Profil bearbeiten", new Icon(VaadinIcon.PENCIL));
        updateProfile.setIconAfterText(true);
        right.addComponentAtIndex(2, updateProfile);

        content.setSizeFull();
        content.getStyle().set("border", "2px solid #9E9E9E");
        data.setWidth("100%");
        data.getStyle().set("text-align", "right");
        data.getStyle().set("border", "1px solid #9E9E9E");
        data.addComponentAsFirst(left);
        data.addComponentAtIndex(1, right);
        content.addComponentAtIndex(1, data);
        add(content);
    }

    //Profil bearbeiten
        //Profilbild
        //Tätigkeitsbeschreibung
        //Adresse
        //Kontodaten

}
