package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.ourCompany.AboutUsViewService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



/**
 *  AboutUs View shows ...
 *
 *  @author Laura Neuendorf, Jessica Reistel
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
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
        PageEntity pageEntity = pageService.findPageById(4);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle, pageContent);
    }

}