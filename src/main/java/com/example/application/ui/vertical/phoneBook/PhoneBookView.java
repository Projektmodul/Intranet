package com.example.application.ui.vertical.phoneBook;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "phoneBook", layout = ContentHolder.class)
@PageTitle("Telefonbuch")

public class PhoneBookView extends Div {

    public PhoneBookView() {
        setId("content-view_blue");
        setClassName("pageContentPosition");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new Anchor("https://www.11880.com", "externes Telefonbuch"));
        verticalLayout.add(new Anchor("https://fldsr274.bsag.local:8443/php-bin/WebClient.php?lang=de", "internes Telefonbuch"));

        add(verticalLayout);
    }

}
