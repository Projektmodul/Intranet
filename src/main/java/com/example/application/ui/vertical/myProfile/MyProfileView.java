package com.example.application.ui.vertical.myProfile;

import com.example.application.backend.entities.AddressEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.addresses.AddressService;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.utils.images.ImagesManager;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
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
 *  @version 4.0
 *  @since 21.12.2020
 *  @lastUpdated 19.01.2021 by Anastasiya Jackwerth and Sabrine Gamdou
 */
@CssImport("./styles/views/main/content.css")
@Route(value = "myProfile", layout = MainView.class)
@PageTitle("Mein Profil")

public class MyProfileView extends Div {

    private ImagesManager imagesManager;

   // private MyProfileViewService myProfileViewService;
    private PageService pageService;
    private UserService userService;
    private ImageService imageService;
    private AddressService addressService;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private AddressEntity addressEntity;

    private TextField updateIban;
    private TextArea updateJobDescription;

    private TextField updateStreet;
    private IntegerField updateNumber;
    private IntegerField updatePostcode;
    private TextField updateCity;

    private Div bigContainer;
    private Div imagesContainer;
    private Div imagesUploader;


    /*
     * Constructor of the MyProfileVew class where the content is added to the view
     */
    public MyProfileView(PageService pageService, UserService userService, AddressService addressService,
                         ImageService imageService) {
        setId("myProfile");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");


        this.pageService = pageService;
        this.userService = userService;
        this.addressService = addressService;

        this.imageService = imageService;

        updateIban = new TextField();
        updateJobDescription = new TextArea();

        updateStreet = new TextField();
        updateNumber = new IntegerField();
        updatePostcode = new IntegerField();
        updateCity = new TextField();

        pageEntity = pageService.findPageById(2); //Only for demo, need to be setted by logged in user
        userEntity = pageEntity.getUser();
        addressEntity = userEntity.getAddress();

        VerticalLayout content = new VerticalLayout();

        initializeImagesManager();
        initializeBigContainer();
        initializeUploadContainer();

        //True means only one image could be added.
        imagesManager.setOneImage(true);

        content.addComponentAsFirst(new H1(pageEntity.getTitle()));
        content.setSizeFull();
        content.addComponentAtIndex(1, initData());




        add(content);
    }
// initializes the entity lists + containers.
    private  void initializeImagesManager(){
        imagesManager = new ImagesManager(pageEntity.getImages(), imageService);
        imagesManager.setImagesEntities(pageEntity.getImages());
        imagesManager.setAllImageEntitiesData(pageEntity,userEntity);

        imagesManager.initializeAllImages();
    }

    private void initializeImages(){
        imagesContainer = new Div();
        for(Image image : imagesManager.getImages()) imagesContainer.add(image);
        bigContainer.add(imagesContainer);
        bigContainer.add(imagesManager);
    }

    private void initializeBigContainer(){
        bigContainer = new Div();
        initializeImages();
    }

    private void initializeUploadContainer(){
        imagesManager.initializeUploadContainer();
        imagesUploader = imagesManager.getImagesUploader();
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

        /*Image profilepicture = new Image("images/user.png", "My Profile Picture");*/
        bigContainer.setHeight("auto");
        bigContainer.setWidth("15rem");
        bigContainer.addClassName("user");

        TextArea jobDescription = new TextArea();
        jobDescription.setValue(userEntity.getJobDescription());
        jobDescription.setLabel("Tätigkeitsbeschreibung");
        jobDescription.setReadOnly(true);

        Button updateProfile = new Button("Profil bearbeiten", new Icon(VaadinIcon.PENCIL));
        updateProfile.addClickListener(e -> initContentDialog().open());
        updateProfile.setIconAfterText(true);


        right.addComponentAsFirst(bigContainer);
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

        Button saveButton = new Button("Speichern", e -> {
            addressService.update(addressEntity, updateStreet, updateNumber,
                     updatePostcode, updateCity);
            userService.update(userEntity, updateIban, updateJobDescription);
            contentDialog.close();
        });
        Button cancelButton = new Button("Abbrechen", e -> contentDialog.close());
        saveButton.addClassName("myProfileButton");
        cancelButton.addClassName("myProfileButton");
        saveCancel.add(saveButton, cancelButton);

        HorizontalLayout update = new HorizontalLayout();


        update.addComponentAsFirst(initUpdateVerticalLayoutLeft());
        update.addComponentAtIndex(1, initUpdateVerticalLayoutRight());
        contentDialog.add(new H1("Profil bearbeiten"),update,  imagesUploader,saveCancel);


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




        updateLeft.addComponentAsFirst(updateIban);
        updateLeft.addComponentAtIndex(1, updateJobDescription);


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
