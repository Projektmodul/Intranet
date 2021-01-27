package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius, Jessica Reistel
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 28.01.2021 by Jessica Reistel
 */
@Route(value = "news", layout = MainView.class)
@PageTitle("Nachrichten")
public class NewsView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private Paragraph pageText;
    private PageEntity pageEntity;

    public NewsView(PageService pageService) {

        setId("news");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        pageEntity = pageService.findPageById(6);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Paragraph(pageEntity.getContent());

        add(pageTitle,pageText);
    }
}
