package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Wiki View shows ...
 *
 *  @author Rebecca Schirmacher
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "wiki", layout = MainView.class)
@PageTitle("Wiki")
public class WikiView extends Div {

    private H1 pageTitle;
    private Span pageText;
    private PageEntity pageEntity;

    public WikiView() {
        setId("wiki");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        pageTitle = new H1("WIKI");
        pageText = new Span("zu Wikipedia...");

        VerticalLayout verticalLayout = new VerticalLayout();

        //link has to be updated with the actual bsag wiki address
        Anchor linkWiki = new Anchor("https://de.wikipedia.org/wiki/Bremer_Stra%C3%9Fenbahn_AG", pageText);
        linkWiki.setTarget("https://de.wikipedia.org/wiki/Bremer_Stra%C3%9Fenbahn_AG");

        verticalLayout.add(linkWiki);
        add(pageTitle, verticalLayout);
    }
}
