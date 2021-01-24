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
 *  AboutUs View shows ...
 *
 *  @author Laura Neuendorf, Jessica Reistel
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

@Route(value = "aboutUs", layout = MainView.class)
@PageTitle("Über Uns")
public class AboutUsView extends Div {

    private PageService pageService;

    private H1 pageTitle;
    private Paragraph pageContent;

    public AboutUsView(PageService pageService) {
        this.pageService = pageService;

        setId("aboutUs");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");


        setData();
    }

    private void setData(){
        PageEntity pageEntity = pageService.findPageById(5);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph();
        pageContent.setId("pageContentWelcome");
        pageContent.setText((pageEntity.getContent()));
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle, pageContent);
    }
}