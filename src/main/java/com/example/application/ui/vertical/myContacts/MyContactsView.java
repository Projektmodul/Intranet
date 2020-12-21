package com.example.application.ui.vertical.myContacts;

import com.example.application.ui.ContentHolder;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "myContacts", layout = ContentHolder.class)
@PageTitle("Meine Kontakte")
public class MyContactsView extends Div {

    public MyContactsView() {
        setId("myContacts-view");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
        add(new Text("GitHub Setup"));
    }
}