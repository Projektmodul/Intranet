package com.example.application.ui.horizontal.library;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Archive View shows a short description and a non-functional button
 *
 *  @author Vanessa Skowronsky
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 18.01.2021
 */
@Route(value = "archive", layout = MainView.class)
@PageTitle("Archiv")
public class ArchiveView extends Div {

    public ArchiveView() {
        setId("archive");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        add(new H1("Archiv"));
        VerticalLayout archiveLayout = new VerticalLayout();
        archiveLayout.add(new Span("Für ältere Dokumente, die Sie nicht bei Unterlagen finden, überpüfen Sie bitte das Dokumentenmanagementsystem."));
        archiveLayout.add(new Button("Zum Dokumentenmanagementsystem"));

        add(archiveLayout);
    }

}
