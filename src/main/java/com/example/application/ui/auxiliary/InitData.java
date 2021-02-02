package com.example.application.ui.auxiliary;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.utils.images.Image;
import com.example.application.backend.utils.images.ImagesManager;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * InitData is a class that contains the setData method for the View classes
 *
 * @author Laura Neuendorf
 * @version 1.0
 * @since 01.02.2021
 */

@CssImport("./styles/views/main/content.css")
public class InitData {
    public static PageService pageService;
    public static PageEntity pageEntity;

    public static LinkService linkService;

    public static UserEntity userEntity;

    public static ImagesManager imagesManager;
    public static ImageService imageService;

    public static H1 pageTitle;
    public static Paragraph pageContent;

    public InitData(PageService pageService){
        this.pageService = pageService;
    }

    public InitData(PageService pageService, LinkService linkService){

        this.pageService = pageService;
        this.linkService = linkService;
    }

    /*
     * This method fetches the data from the database
     * and displays it on the corresponding page
     *
     * @param int pageId
     * @return dataSet
     */
    public static Component setData(int pageId){

        VerticalLayout dataSet = new VerticalLayout();
        dataSet.setId("dataSetLayout");

        pageEntity = pageService.findPageById(pageId);
        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph();
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        dataSet.add(pageTitle, pageContent);
        return dataSet;
    }

    /*
     * This method fetches the link data from the database
     * and displays it on the corresponding page
     *
     * @param int linkId
     * @return linkSet
     */
    public static  Component setLinkData(int linkId){
        HorizontalLayout linkSet = new HorizontalLayout();
        linkSet.setId("dataSetLayout");

        LinkEntity linkEntity = linkService.findById(linkId);
        Anchor mailLink = new Anchor(linkEntity.getUrl(), linkEntity.getTitle());

        linkSet.add(mailLink);
        return linkSet;
    }
}
