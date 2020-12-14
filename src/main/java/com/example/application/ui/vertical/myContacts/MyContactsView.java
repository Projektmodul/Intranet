package com.example.application.ui.vertical.myContacts;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "myContacts", layout = MainView.class)
@PageTitle("Meine Kontakte")
public class MyContactsView extends Div {

    public MyContactsView() {
        setId("myContacts-view");
        add(new Text("Content placeholder"));
    }
}