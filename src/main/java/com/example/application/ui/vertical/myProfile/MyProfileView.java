package com.example.application.ui.vertical.myProfile;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "myProfile", layout = MainView.class)
@PageTitle("Mein Profil")
public class MyProfileView extends Div {

    public MyProfileView() {
        setId("myProfile-view");
        add(new Text("Content placeholder"));
    }

}
