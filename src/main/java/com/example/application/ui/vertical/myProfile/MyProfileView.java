package com.example.application.ui.vertical.myProfile;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

import java.awt.*;

@Route(value = "myProfile", layout = MainView.class)
@PageTitle("Mein Profil")
public class MyProfileView extends Div {

    public MyProfileView() {
        setId("myProfile-view");

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();

        content.addComponentAsFirst(new Label("Mein Profil"));

        TextField firstname = new TextField();
        firstname.setValue("Test");
        firstname.setLabel("Vorname");
        firstname.setReadOnly(true);
        content.addComponentAtIndex(1, firstname);

        TextField lastname = new TextField();
        lastname.setValue("Test");
        lastname.setLabel("Nachname");
        lastname.setReadOnly(true);
        content.addComponentAtIndex(2, lastname);

        TextField email = new TextField();
        email.setValue("Test");
        email.setLabel("E-Mail");
        email.setReadOnly(true);
        content.addComponentAtIndex(3, email);

        TextField telephone = new TextField();
        telephone.setValue("Test");
        telephone.setLabel("Telefonnummer");
        telephone.setReadOnly(true);
        content.addComponentAtIndex(4, telephone);

        TextField center = new TextField();
        center.setValue("Test");
        center.setLabel("Center");
        center.setReadOnly(true);
        content.addComponentAtIndex(5, center);

        TextField roomnumber = new TextField();
        roomnumber.setValue("Test");
        roomnumber.setLabel("Raumnummer");
        roomnumber.setReadOnly(true);
        content.addComponentAtIndex(6, roomnumber);

        TextField address = new TextField();
        address.setValue("Test");
        address.setLabel("Adresse");
        address.setReadOnly(true);
        content.addComponentAtIndex(7, address);

        TextField accountDetails = new TextField();
        accountDetails.setValue("Test");
        accountDetails.setLabel("Kontodaten");
        accountDetails.setReadOnly(true);
        content.addComponentAtIndex(8, accountDetails);

        TextField profilepicture = new TextField();
        profilepicture.setValue("Test");
        profilepicture.setLabel("Profilbild");
        profilepicture.setReadOnly(true);
        content.addComponentAtIndex(9, profilepicture);

        TextField job_description = new TextField();
        job_description.setValue("Test");
        job_description.setLabel("Tätigkeitsbeschreibung");
        job_description.setReadOnly(true);
        content.addComponentAtIndex(10, job_description);

        add(content);
    }



    //Vor- und Nachname
    //E-Mailadresse
    //Telefonnummer
    //Center
    //Raumnummer
    //Adresse
    //Kontodaten
    //Profilbild
    //Tätikeitsbeschreibung

    //Profil bearbeiten
        //Profilbild
        //Tätigkeitsbeschreibung
        //Adresse
        //Kontodaten

}
