package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius, Jessica Reistel
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Jessica Reistel
 */
@Route(value = "media", layout = MainView.class)
@PageTitle("Medien")
public class MediaView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private Paragraph pageText;
    private PageEntity pageEntity;

    public MediaView(PageService pageService) {
        pageEntity = pageService.findPageById(16);

        pageTitle = new H1(pageEntity.getTitle());

        setId("media");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");
        add(pageTitle);
    }
}
