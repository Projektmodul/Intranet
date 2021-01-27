package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Laura Neuendorf
 */
@Route(value = "sport", layout = MainView.class)
@PageTitle("Sport&Freizeit")
public class SportView extends Div {
    private PageService pageService;
    private PageEntity pageEntity;
    private UserService userService;
    private UserEntity userEntity;
    private GetUserController getUserController;

    private String username;
    private H1 pageTitle;
    private Paragraph pageContent;

    private TextField updateWeekday;
    private TextField updateTime;
    private TextField updateActivity;

    public SportView(PageService pageService, UserService userService) {
        setId("sport");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        this.pageService = pageService;
        this.userService = userService;
        getUserController = new GetUserController();

        setData();
        showActivities();

        username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
    }

    private void setData(){
        pageEntity = pageService.findPageById(7);

        pageTitle = new H1(pageEntity.getTitle());

        pageContent = new Paragraph();
        pageContent.setId("pageContentSportView");
        pageContent.setText(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        add(pageTitle, pageContent);
    }

    private VerticalLayout showActivities(){
        VerticalLayout activities = new VerticalLayout();

        HorizontalLayout firstActivity = new HorizontalLayout();
        HorizontalLayout secondActivity = new HorizontalLayout();
        HorizontalLayout thirdActivity = new HorizontalLayout();

        TextField weekday = new TextField();
        weekday.setValue("Montag");
        weekday.setLabel("Wochentag");
        weekday.setReadOnly(true);

        TextField time = new TextField();
        time.setValue("17:30 Uhr");
        time.setLabel("Uhrzeit");
        time.setReadOnly(true);

        TextField activity = new TextField();
        activity.setValue("Fußball");
        activity.setLabel("Aktivität");
        activity.setReadOnly(true);

        TextField weekdayOne = new TextField();
        weekdayOne.setValue("Mittwoch");
        weekdayOne.setReadOnly(true);

        TextField timeOne = new TextField();
        timeOne.setValue("17:00 Uhr");
        timeOne.setReadOnly(true);

        TextField activityOne = new TextField();
        activityOne.setValue("Aerobic");
        activityOne.setReadOnly(true);

        TextField weekdayTwo = new TextField();
        weekdayTwo.setValue("Freitag");
        weekdayTwo.setReadOnly(true);

        TextField timeTwo = new TextField();
        timeTwo.setValue("18:00 Uhr");
        timeTwo.setReadOnly(true);

        TextField activityTwo = new TextField();
        activityTwo.setValue("Aerobic");
        activityTwo.setReadOnly(true);

        Button updateActivity = new Button("Aktivitäten bearbeiten", new Icon(VaadinIcon.PENCIL));
        updateActivity.addClickListener(e -> initUpdateDialog().open());
        updateActivity.setIconAfterText(true);

        firstActivity.addComponentAsFirst(weekday);
        firstActivity.addComponentAtIndex(1, time);
        firstActivity.addComponentAtIndex(2, activity);
        activities.addComponentAsFirst(firstActivity);

        secondActivity.addComponentAsFirst(weekdayOne);
        secondActivity.addComponentAtIndex(1, timeOne);
        secondActivity.addComponentAtIndex(2, activityOne);
        activities.addComponentAtIndex(1, secondActivity);

        thirdActivity.addComponentAsFirst(weekdayTwo);
        thirdActivity.addComponentAtIndex(1, timeTwo);
        thirdActivity.addComponentAtIndex(2, activityTwo);
        activities.addComponentAtIndex(2, thirdActivity);

        activities.addComponentAtIndex(3, updateActivity);

        this.add(activities);

        return activities;
    }

    private Dialog initUpdateDialog(){
        Dialog updateDialog = new Dialog();

        updateDialog.setCloseOnOutsideClick(false);
        updateDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();

        Button saveButton = new Button("Speichern");
        Button cancelButton = new Button ("Abbrechen", e -> updateDialog.close());

        saveCancel.add(saveButton, cancelButton);

        updateDialog.add(new H1("Vorhandene Aktivitäten bearbeiten"), saveCancel);

        return updateDialog;
    }

    private HorizontalLayout updateLayout(){
        HorizontalLayout update = new HorizontalLayout();

        return update;
    }
}
