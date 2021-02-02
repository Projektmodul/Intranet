package com.example.application.ui.horizontal.ourCompany;


import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Sport View shows sporting activities offered by BSAG for its employees
 *
 *  @author Monika Martius, Jessica Reistel, Laura Neuendorf
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 01.02.2021 by Laura Neuendorf
 */
@Route(value = "sport", layout = MainView.class)
@PageTitle("Sport&Freizeit")
public class SportView extends Div {
    private UserEntity userEntity;

    private TextField updateWeekday;
    private TextField updateTime;
    private TextField updateActivity;

    private TextField updateWeekdayOne;
    private TextField updateTimeOne;
    private TextField updateActivityOne;

    private TextField updateWeekdayTwo;
    private TextField updateTimeTwo;
    private TextField updateActivityTwo;

    public SportView(PageService pageService, UserService userService) {
        setId("sport");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        GetUserController getUserController = new GetUserController();

        updateWeekday = new TextField();
        updateTime = new TextField();
        updateActivity = new TextField();

        updateWeekdayOne = new TextField();
        updateTimeOne = new TextField();
        updateActivityOne = new TextField();

        updateWeekdayTwo = new TextField();
        updateTimeTwo = new TextField();
        updateActivityTwo = new TextField();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Unser Unternehmen"), new Breadcrumb("Sport und Freizeit"));

        InitData initSport = new InitData(pageService);
        this.add(breadcrumbs, initSport.setData(7));

        showActivities();

        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
    }

    /**
     * This method creates a vertical layout in which the offered activities are listed
     * @return activities as a vertical layout
     */
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
        activityTwo.setValue("Handball");
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

    /**
     * This method initializes a content dialog window with two buttons
     * @return updateDialog as a dialog
     */
    private Dialog initUpdateDialog(){
        Dialog updateDialog = new Dialog();

        updateDialog.setCloseOnOutsideClick(false);
        updateDialog.setCloseOnEsc(false);

        Div saveCancel = new Div();

        Button saveButton = new Button("Speichern", e -> updateDialog.close());
        Button cancelButton = new Button ("Abbrechen", e -> updateDialog.close());

        saveCancel.add(saveButton, cancelButton);

        HorizontalLayout update = new HorizontalLayout();
        update.addComponentAsFirst(updateLayout());

        updateDialog.add(new H1("Vorhandene Aktivitäten bearbeiten"),update, saveCancel);

        return updateDialog;
    }

    /**
     * This method fills the dialog updateDialog with content
     * @return updateData as a vertical layout
     */
    private VerticalLayout updateLayout(){
        VerticalLayout updateData = new VerticalLayout();

        HorizontalLayout updateActivityFirst = new HorizontalLayout();
        HorizontalLayout updateActivitySecond = new HorizontalLayout();
        HorizontalLayout updateActivityThird = new HorizontalLayout();

        updateWeekday.setValue("Montag");
        updateWeekday.setLabel("Wochentag");
        updateTime.setValue("17:30 Uhr");
        updateTime.setLabel("Uhrzeit");
        updateActivity.setValue("Fußball");
        updateActivity.setLabel("Aktivität");

        updateWeekdayOne.setValue("Mittwoch");
        updateTimeOne.setValue("17:00 Uhr");
        updateActivityOne.setValue("Aerobic");

        updateWeekdayTwo.setValue("Freitag");
        updateTimeTwo.setValue("18:00 Uhr");
        updateActivityTwo.setValue("Handball");

        updateActivityFirst.addComponentAsFirst(updateWeekday);
        updateActivityFirst.addComponentAtIndex(1, updateTime);
        updateActivityFirst.addComponentAtIndex(2, updateActivity);

        updateActivitySecond.addComponentAsFirst(updateWeekdayOne);
        updateActivitySecond.addComponentAtIndex(1, updateTimeOne);
        updateActivitySecond.addComponentAtIndex(2, updateActivityOne);

        updateActivityThird.addComponentAsFirst(updateWeekdayTwo);
        updateActivityThird.addComponentAtIndex(1, updateTimeTwo);
        updateActivityThird.addComponentAtIndex(2, updateActivityTwo);

        updateData.addComponentAsFirst(updateActivityFirst);
        updateData.addComponentAtIndex(1, updateActivitySecond);
        updateData.addComponentAtIndex(2, updateActivityThird);

        return updateData;
    }
}
