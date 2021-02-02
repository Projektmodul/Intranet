package com.example.application.ui.vertical.help;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.example.application.ui.horizontal.library.FAQView;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 *  Help View shows ...
 *
 *  @author Jessica Reistel
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 02.02.2021 by Laura Neuendorf
 */
@Route(value = "help", layout = MainView.class)
@PageTitle("Hilfe")
public class HelpView extends Div {

    private PageService pageService;
    private PageEntity pageEntity;
    private LinkService linkService;

    public HelpView(PageService pageService, LinkService linkService) {
        setId("help");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;


        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Hilfe"));

        RouterLink faqLink = new RouterLink("Antworten auf häufig gestellte Fragen", FAQView.class);

        InitData initHelp = new InitData(pageService, linkService);
        this.add(breadcrumbs, initHelp.setData(25), initHelp.setLinkData(3), faqLink);
    }
}
