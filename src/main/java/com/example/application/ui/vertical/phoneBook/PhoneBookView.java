package com.example.application.ui.vertical.phoneBook;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "phoneBook", layout = MainView.class)
@PageTitle("Telefonbuch")
public class PhoneBookView extends Div {

    public PhoneBookView() {
        setId("phoneBook-view");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
    }

}
