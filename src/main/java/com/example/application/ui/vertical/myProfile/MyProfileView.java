package com.example.application.ui.vertical.myProfile;

import com.example.application.backend.entities.UsersEntity;
import com.example.application.backend.entities.AddressesEntity;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

/**
 * MyProfile View
 *
 * @author Jessica Reistel and Laura Neuendorf
 * @version 2.0
 * @since 21.12.2020
 */

@Route(value = "myProfile", layout = MainView.class)
@PageTitle("Mein Profil")
@CssImport("./styles/views/main/myProfileView.css")
public class MyProfileView extends Div {

    private UsersEntity usersEntity = new UsersEntity(1, "firstname", "surname", 3, "DE1273462537274", 'I', 5, 556434, "email", "wir sind toll" );
    private AddressesEntity addressesEntity = new AddressesEntity(1,"Beispielstraße", 123, 28359, "Bremen");

    public MyProfileView() {
        setId("myProfile-view");

        VerticalLayout content = new VerticalLayout();
        content.addComponentAsFirst(new Label("Mein Profil"));
        content.setSizeFull();
        content.getStyle().set("border", "2px solid #9E9E9E");
        content.addComponentAtIndex(1, initData());

        add(content);
    }

    private HorizontalLayout initData () {
        HorizontalLayout data = new HorizontalLayout();

        data.setWidth("100%");
        data.getStyle().set("text-align", "right");
        data.getStyle().set("border", "1px solid #9E9E9E");

        data.addComponentAsFirst(initVerticalLayoutLeft());
        data.addComponentAtIndex(1, initVerticalLayoutRight());

        return data;
    }

    private VerticalLayout initVerticalLayoutLeft () {
        VerticalLayout left = new VerticalLayout();

        HorizontalLayout name = new HorizontalLayout();
        HorizontalLayout emailPhone = new HorizontalLayout();
        HorizontalLayout centerRoom = new HorizontalLayout();
        HorizontalLayout addressIban = new HorizontalLayout();

        TextField firstname = new TextField();
        firstname.setValue(usersEntity.getFirstname());
        firstname.setLabel("Vorname");
        firstname.setReadOnly(true);

        TextField surname = new TextField();
        surname.setValue(usersEntity.getSurname());
        surname.setLabel("Nachname");
        surname.setReadOnly(true);

        TextField email = new TextField();
        email.setValue(usersEntity.getEmail());
        email.setLabel("E-Mail");
        email.setReadOnly(true);

        TextField telephone = new TextField();
        telephone.setValue(usersEntity.getTelefon());
        telephone.setLabel("Telefonnummer");
        telephone.setReadOnly(true);

        TextField center = new TextField();
        center.setValue(usersEntity.getCenter());
        center.setLabel("Center");
        center.setReadOnly(true);

        TextField roomnumber = new TextField();
        roomnumber.setValue(usersEntity.getRoomnumber());
        roomnumber.setLabel("Raumnummer");
        roomnumber.setReadOnly(true);

        TextArea address = new TextArea();
        address.setValue(addressesEntity.getStreetname()+" "+ addressesEntity.getStreetnumber()+"\n"+
                addressesEntity.getPostcode()+" "+ addressesEntity.getCity());
        address.setLabel("Adresse");
        address.setReadOnly(true);

        TextField iban = new TextField();
        iban.setValue(usersEntity.getIban());
        iban.setLabel("Kontodaten");
        iban.setReadOnly(true);


        name.addComponentAsFirst(firstname);
        name.addComponentAtIndex(1, surname);
        left.addComponentAsFirst(name);

        emailPhone.addComponentAsFirst(email);
        emailPhone.addComponentAtIndex(1, telephone);
        left.addComponentAtIndex(1, emailPhone);

        centerRoom.addComponentAsFirst(center);
        centerRoom.addComponentAtIndex(1, roomnumber);
        left.addComponentAtIndex(2, centerRoom);

        addressIban.addComponentAsFirst(address);
        addressIban.addComponentAtIndex(1, iban);
        left.addComponentAtIndex(3, addressIban);

        return left;
    }

    private VerticalLayout initVerticalLayoutRight () {
        VerticalLayout right = new VerticalLayout();

        Image profilepicture = new Image("images/user.png", "My Profile Picture");
        profilepicture.setHeight("auto");
        profilepicture.setWidth("15rem");
        profilepicture.addClassName("user");

        TextField jobDescription = new TextField();
        jobDescription.setValue(usersEntity.getJobDescription());
        jobDescription.setLabel("Tätigkeitsbeschreibung");
        jobDescription.setReadOnly(true);

        Button updateProfile = new Button("Profil bearbeiten", new Icon(VaadinIcon.PENCIL));
        updateProfile.addClickListener(e -> initContentDialog().open());
        updateProfile.setIconAfterText(true);


        right.addComponentAsFirst(profilepicture);
        right.addComponentAtIndex(1, jobDescription);
        right.addComponentAtIndex(2, updateProfile);

        return right;
    }

    private Dialog initContentDialog () {
        Dialog contentDialog = new Dialog();
        contentDialog.setCloseOnOutsideClick(false);
        contentDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();
        saveCancel.addClassName("saveCancelButton");

        Button saveButton = new Button("Save", e -> contentDialog.close());
        Button cancelButton = new Button("Cancel", e -> contentDialog.close());
        saveButton.setId("button");
        cancelButton.setId("button");
        saveCancel.add(saveButton, cancelButton);

        HorizontalLayout update = new HorizontalLayout();


        update.addComponentAsFirst(initUpdateVerticalLayoutLeft());
        update.addComponentAtIndex(1, initUpdateVerticalLayoutRight());
        contentDialog.add(new H1("Profil bearbeiten"), update, saveCancel);

        return contentDialog;
    }

    private VerticalLayout initUpdateVerticalLayoutLeft () {
        VerticalLayout updateLeft = new VerticalLayout();

        TextField updateIban = new TextField();
        updateIban.setValue(usersEntity.getIban());
        updateIban.setLabel("Kontodaten");

        TextArea updateJobDescription = new TextArea();
        updateJobDescription.setValue(usersEntity.getJobDescription());
        updateJobDescription.setLabel("Tätigkeitsbeschreibung");

        Button updateProfilpicture = new Button("Bild hochladen", new Icon(VaadinIcon.UPLOAD));
        updateProfilpicture.setIconAfterText(true);


        updateLeft.addComponentAsFirst(updateIban);
        updateLeft.addComponentAtIndex(1, updateJobDescription);
        updateLeft.addComponentAtIndex(2, updateProfilpicture);

        return updateLeft;
    }

    private VerticalLayout initUpdateVerticalLayoutRight () {
        VerticalLayout updateRight = new VerticalLayout();

        HorizontalLayout addressNumber = new HorizontalLayout();
        HorizontalLayout postcodeCity = new HorizontalLayout();

        TextField updateStreet = new TextField();
        updateStreet.setValue(addressesEntity.getStreetname());
        updateStreet.setLabel("Straße");

        IntegerField updateNumber = new IntegerField();
        updateNumber.setValue(addressesEntity.getStreetnumber());
        updateNumber.setLabel("Hausnummer");

        IntegerField updatePostcode = new IntegerField();
        updatePostcode.setValue(addressesEntity.getPostcode());
        updatePostcode.setLabel("PLZ");

        TextField updateCity = new TextField();
        updateCity.setValue(addressesEntity.getCity());
        updateCity.setLabel("Stadt");


        addressNumber.addComponentAsFirst(updateStreet);
        addressNumber.addComponentAtIndex(1,updateNumber);
        updateRight.addComponentAsFirst(addressNumber);

        postcodeCity.addComponentAsFirst(updatePostcode);
        postcodeCity.addComponentAtIndex(1, updateCity);
        updateRight.addComponentAtIndex(1, postcodeCity);

        return updateRight;
    }

}
