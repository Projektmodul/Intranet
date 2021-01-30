package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius and Laura Neuendorf
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 28.01.2021 by Laura Neuendorf
 */
@Route(value = "fAQ", layout = MainView.class)
@PageTitle("FAQ")
public class FAQView extends Div {
    private PageService pageService;
    private PageEntity pageEntity;

    private LinkService linkService;

    private H1 pageTitle;
    private Paragraph pageContent;


    public FAQView(PageService pageService, LinkService linkService) {
        setId("fAQ");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;

        setData();

    }

    /*
     * This method fetches the data from the database
     * and displays it on the corresponding page
     */
    public void setData(){
        pageEntity = pageService.findPageById(17);
        pageTitle = new H1(pageEntity.getTitle());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        pageContent = new Paragraph();
        pageContent.setText(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        LinkEntity linkEntity = linkService.findById(4);
        Anchor mailLink = new Anchor(linkEntity.getUrl(), linkEntity.getTitle());

        this.add(breadcrumbs, pageTitle, pageContent, mailLink);
    }
}
