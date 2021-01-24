package com.example.application.ui.vertical.myProfile;

import com.example.application.backend.entities.AddressEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.addresses.AddressService;
import com.example.application.backend.services.myProfile.MyProfileViewService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
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
 *  @version 3.0
 *  @since 21.12.2020
 *  @lastUpdated 16.01.2021
 */
@CssImport("./styles/views/main/content.css")
@Route(value = "myProfile", layout = MainView.class)
@PageTitle("Mein Profil")

public class MyProfileView extends Div {

    private MyProfileViewService myProfileViewService;
    private UserService userService;
    private AddressService addressService;
    private PageEntity pageEntity;
    private UserEntity userEntity;
    private AddressEntity addressEntity;
    private GetUserController getUserController;
    private String username;

    private TextField updateIban;
    private TextArea updateJobDescription;

    private TextField updateStreet;
    private IntegerField updateNumber;
    private IntegerField updatePostcode;
    private TextField updateCity;

    /*
     * Constructor of the MyProfileVew class where the content is added to the view
     */
    public MyProfileView(MyProfileViewService myProfileViewService, UserService userService, AddressService addressService) {
        setId("myProfile");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.myProfileViewService = myProfileViewService;
        this.userService = userService;
        this.addressService = addressService;

        getUserController = new GetUserController();

        updateIban = new TextField();
        updateJobDescription = new TextArea();

        updateStreet = new TextField();
        updateNumber = new IntegerField();
        updatePostcode = new IntegerField();
        updateCity = new TextField();

        pageEntity = myProfileViewService.findPageById(22); //Only for demo, need to be setted by logged in user

        username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        addressEntity = userEntity.getAddress();

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
        firstname.setValue(userEntity.getFirstName());
        firstname.setLabel("Vorname");
        firstname.setReadOnly(true);

        TextField surname = new TextField();
        surname.setValue(userEntity.getSurname());
        surname.setLabel("Nachname");
        surname.setReadOnly(true);

        TextField email = new TextField();
        email.setValue(userEntity.getEmail());
        email.setLabel("E-Mail");
        email.setReadOnly(true);

        TextField telephone = new TextField();
        telephone.setValue(userEntity.getTelephoneNumber());
        telephone.setLabel("Telefonnummer");
        telephone.setReadOnly(true);

        TextField center = new TextField();
        center.setValue(userEntity.getCenter());
        center.setLabel("Center");
        center.setReadOnly(true);

        TextField roomnumber = new TextField();
        roomnumber.setValue(userEntity.getRoomNumber());
        roomnumber.setLabel("Raumnummer");
        roomnumber.setReadOnly(true);

        TextArea address = new TextArea();
        address.setValue(addressEntity.getStreetName()+" "+ addressEntity.getStreetNumber()+"\n"+
                addressEntity.getPostcode()+" "+ addressEntity.getCity());
        address.setLabel("Adresse");
        address.setReadOnly(true);

        TextField iban = new TextField();
        iban.setWidth("250px");
        iban.setValue(userEntity.getIban());
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
        jobDescription.setValue(userEntity.getJobDescription());
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

        Button saveButton = new Button("Save", e -> {
            addressService.update(addressEntity, updateStreet, updateNumber,
                    updatePostcode, updateCity);
            userService.update(userEntity, updateIban, updateJobDescription);
            contentDialog.close();
        });
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


        updateIban.setValue(userEntity.getIban());
        updateIban.setLabel("Kontodaten");


        updateJobDescription.setValue(userEntity.getJobDescription());
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

        updateStreet.setValue(addressEntity.getStreetName());
        updateStreet.setLabel("Straße");


        updateNumber.setValue(addressEntity.getStreetNumber());
        updateNumber.setLabel("Hausnummer");


        updatePostcode.setValue(addressEntity.getPostcode());
        updatePostcode.setLabel("PLZ");


        updateCity.setValue(addressEntity.getCity());
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
