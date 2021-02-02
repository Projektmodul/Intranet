package com.example.application.ui.vertical.phoneBook;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  phoneBook View shows ...
 *
 *  @author Rebecca Schirmacher, Jessica Reistel
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 02.02.2021 by Laura Neuendorf
 */
@Route(value = "phoneBook", layout = MainView.class)
@PageTitle("Telefonbuch")

public class PhoneBookView extends Div {
    private PageService pageService;
    private PageEntity pageEntity;
    private LinkService linkService;

    public PhoneBookView(PageService pageService, LinkService linkService) {
        setId("phoneBook");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Telefonbuch"));

        InitData initPhoneBook = new InitData(pageService, linkService);

        VerticalLayout phoneLinks = new VerticalLayout();
        phoneLinks.add(initPhoneBook.setLinkData(8), initPhoneBook.setLinkData(9));

        this.add(breadcrumbs, initPhoneBook.setData(23), phoneLinks);
    }
}
