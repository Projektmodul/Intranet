package com.example.application.ui.vertical.phoneBook;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  phoneBook View shows ...
 *
 *  @author Rebecca Schirmacher
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
 */
@Route(value = "phoneBook", layout = MainView.class)
@PageTitle("Telefonbuch")

public class PhoneBookView extends Div {

    public PhoneBookView() {
        setId("phoneBook");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new Anchor("https://www.11880.com", "externes Telefonbuch"));
        verticalLayout.add(new Anchor("https://fldsr274.bsag.local:8443/php-bin/WebClient.php?lang=de", "internes Telefonbuch"));

        add(verticalLayout);
    }

}
