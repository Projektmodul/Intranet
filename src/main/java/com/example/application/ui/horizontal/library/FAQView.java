package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius, Jessica Reistel, Laura Neuendorf
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 01.02.2021 by Laura Neuendorf
 */
@Route(value = "fAQ", layout = MainView.class)
@PageTitle("FAQ")
public class FAQView extends Div {
    private PageService pageService;
    private PageEntity pageEntity;
    private LinkService linkService;
    


    public FAQView(PageService pageService, LinkService linkService) {
        setId("fAQ");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb("FAQ"));

        InitData initFAQ = new InitData(pageService, linkService);
        this.add(breadcrumbs, initFAQ.setData(17), initFAQ.setLinkData(4));
    }
}
