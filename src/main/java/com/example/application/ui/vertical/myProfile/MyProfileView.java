package com.example.application.ui.vertical.myProfile;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

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


        add(content);
    }

}
