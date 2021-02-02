package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

/**
 *  Media View shows how the media "images", "videos" and "press releases" could be displayed
 *
 *  @author Monika Martius and Laura Neuendorf
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 02.02.2021 by Vanessa Skowronsky
 */
@Route(value = "media", layout = MainView.class)
@PageTitle("Medien")
public class MediaView extends Div{
    private PageService pageService;
    private PageEntity pageEntity;
    private H1 pageTitle;


    public MediaView(PageService pageService){
        setId("media");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        this.pageService = pageService;

        setContent();
        setTabs();
    }

    /**
     * This method fetches the data from the database
     * and displays it on the corresponding page
     */
    public void setContent(){
        pageEntity = pageService.findPageById(16);
        pageTitle = new H1(pageEntity.getTitle());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        this.add(breadcrumbs, pageTitle);
    }

    /**
     * This method creates a tab menu with three tabs.
     * Furthermore, three pages are created, which are connected to the tabs.
     */
    public void setTabs(){
        Tab picture = new Tab("Bilder");
        VerticalLayout pagePicture = new VerticalLayout();
        pagePicture.add(new Span("Hier können Bilder angezeigt werden"));
        pagePicture.add(new Icon(VaadinIcon.PICTURE));


        Tab videos = new Tab("Videos");
        VerticalLayout pageVideos = new  VerticalLayout();
        pageVideos.add(new Span("Hier können Videos angezeigt werden"));
        pageVideos.add(new Icon(VaadinIcon.FILM));
        pageVideos.setVisible(false);

        Tab pressReleases = new Tab("Pressemitteilungen");
        Paragraph pagePressReleases = new Paragraph();
        pagePressReleases.getElement().setProperty("innerHTML", pageEntity.getContent());
        pagePressReleases.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(picture, pagePicture);
        tabsToPages.put(videos, pageVideos);
        tabsToPages.put(pressReleases, pagePressReleases);
        Tabs allTabs = new Tabs(picture, videos, pressReleases);
        Div allPages = new Div(pagePicture, pageVideos, pagePressReleases);

        allTabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(allTabs.getSelectedTab());
            selectedPage.setVisible(true);
        });

        add(allTabs, allPages);
    }
}
