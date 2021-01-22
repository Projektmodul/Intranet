package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;

import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Welcome View shows ...
 *
 *  @author Sabrine Gamdou, Jessica Reistel, Laura Neuendorf
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */
@Route(value = "welcome", layout = MainView.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    private PageService pageService;

    private H1 pageTitle;
    private Paragraph pageContent;

    public WelcomeView(PageService pageService) {
        this.pageService = pageService;

        setId("welcome");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();

    }

    private void setData(){
        PageEntity pageEntity = pageService.findPageById(4);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph();
        pageContent.setId("pageContentWelcome");
        pageContent.setText(pageEntity.getContent());
        /*pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());*/

        this.add(pageTitle,pageContent);
    }

}
