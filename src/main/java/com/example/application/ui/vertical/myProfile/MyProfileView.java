package com.example.application.ui.vertical.myProfile;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UsersEntity;
import com.example.application.backend.entities.AddressesEntity;

import com.example.application.backend.services.myProfile.MyProfileViewService;
import com.example.application.backend.services.users.UsersService;
import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  MyProfile View shows the information from the current user
 *  The user is able to change his profile information
 *
 *  @author Jessica Reistel and Laura Neuendorf
 *  @version 2.0
 *  @since 21.12.2020
 */
@CssImport("./styles/views/main/content.css")
@Route(value = "myProfile", layout = ContentHolder.class)
@PageTitle("Mein Profil")

public class MyProfileView extends Div {
    //These are only for demo, could be deleted once the data in the database is ready to be used
    //private AddressesEntity addressesEntity = new AddressesEntity(1,"Beispielstraße", 123, 28359, "Bremen");

    private MyProfileViewService myProfileViewService;
    private PageEntity pageEntity;
    private UsersEntity usersEntity;
    private AddressesEntity addressesEntity;



    /*
     * Constructor of the MyProfileVew class where the content is added to the view
     */
    public MyProfileView(MyProfileViewService myProfileViewService) {
        this.myProfileViewService = myProfileViewService;

        setId("myProfile-view");
        setClassName("pageContentPosition");

        pageEntity = myProfileViewService.findPageById(2); //Only for demo, need to be setted by logged in user
        usersEntity = pageEntity.getUser();
        addressesEntity = usersEntity.getAddress();

        VerticalLayout content = new VerticalLayout();
        content.addComponentAsFirst(new H1(pageEntity.getTitle()));
        content.setSizeFull();
        content.addComponentAtIndex(1, initData());

        add(content);
    }

    /*
     * The method initData generates the horizontal layout data and adds two vertical layouts
     * @return data
     */
    private HorizontalLayout initData () {
        HorizontalLayout data = new HorizontalLayout();

        data.setSizeFull();

        data.addComponentAsFirst(initVerticalLayoutLeft());
        data.addComponentAtIndex(1, initVerticalLayoutRight());

        return data;
    }

    /*
     * The method initVerticalLayoutLeft generates the left vertical layout for data
     * Includes the text fields firstname, surname, email, telephone, center, roomnumber, address and iban
     * @return left
     */
    private VerticalLayout initVerticalLayoutLeft () {
        VerticalLayout left = new VerticalLayout();

        HorizontalLayout name = new HorizontalLayout();
        HorizontalLayout emailPhone = new HorizontalLayout();
        HorizontalLayout centerRoom = new HorizontalLayout();
        HorizontalLayout addressIban = new HorizontalLayout();

        TextField firstname = new TextField();
        firstname.setValue(usersEntity.getFirstName());
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
        telephone.setValue(usersEntity.getTelephoneNumber());
        telephone.setLabel("Telefonnummer");
        telephone.setReadOnly(true);

        TextField center = new TextField();
        center.setValue(usersEntity.getCenter());
        center.setLabel("Center");
        center.setReadOnly(true);

        TextField roomnumber = new TextField();
        roomnumber.setValue(usersEntity.getRoomNumber());
        roomnumber.setLabel("Raumnummer");
        roomnumber.setReadOnly(true);

        TextArea address = new TextArea();
        address.setValue(addressesEntity.getStreetName()+" "+ addressesEntity.getStreetNumber()+"\n"+
                addressesEntity.getPostcode()+" "+ addressesEntity.getCity());
        address.setLabel("Adresse");
        address.setReadOnly(true);

        TextField iban = new TextField();
        iban.setWidth("250px");
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

    /*
     * The method initVerticalLayoutRight generates the right vertical layout for data
     * Includes the text field job description, the profile picture and the button updateProfile
     * @return right
     */
    private VerticalLayout initVerticalLayoutRight () {
        VerticalLayout right = new VerticalLayout();

        Image profilepicture = new Image("images/user.png", "My Profile Picture");
        profilepicture.setHeight("auto");
        profilepicture.setWidth("15rem");
        profilepicture.addClassName("user");

        TextArea jobDescription = new TextArea();
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

    /*
     * The method initContentDialog generates a dialog window with a horizontal layout update,
     * where the user can update his profile
     * @return contentDialog
     */
    private Dialog initContentDialog () {
        Dialog contentDialog = new Dialog();
        contentDialog.setCloseOnOutsideClick(false);
        contentDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();
        saveCancel.setId("saveCancelDiv");

        Button saveButton = new Button("Save", e -> contentDialog.close());
        Button cancelButton = new Button("Cancel", e -> contentDialog.close());
        saveButton.addClassName("myProfileButton");
        cancelButton.addClassName("myProfileButton");
        saveCancel.add(saveButton, cancelButton);

        HorizontalLayout update = new HorizontalLayout();


        update.addComponentAsFirst(initUpdateVerticalLayoutLeft());
        update.addComponentAtIndex(1, initUpdateVerticalLayoutRight());
        contentDialog.add(new H1("Profil bearbeiten"), update, saveCancel);

        return contentDialog;
    }

    /*
     * The method initUpdateVerticalLayoutLeft generates the left vertical layout for update
     * @return updateLeft
     */
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

    /*
     * The method initUpdateVerticalLayoutRight generates the right vertical layout for update
     * @return updateRight
     */
    private VerticalLayout initUpdateVerticalLayoutRight () {
        VerticalLayout updateRight = new VerticalLayout();

        HorizontalLayout addressNumber = new HorizontalLayout();
        HorizontalLayout postcodeCity = new HorizontalLayout();

        TextField updateStreet = new TextField();
        updateStreet.setValue(addressesEntity.getStreetName());
        updateStreet.setLabel("Straße");

        IntegerField updateNumber = new IntegerField();
        updateNumber.setValue(addressesEntity.getStreetNumber());
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
