package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;

import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.nils_bauer.PureTimeline;
import de.nils_bauer.PureTimelineItem;


/**
 *  AboutUs View shows ...
 *
 *  @author Laura Neuendorf, Jessica Reistel
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 21.01.2021
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
        setTimeline();
    }

    private void setData(){
        PageEntity pageEntity = pageService.findPageById(5);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle, pageContent);
    }

    private void setTimeline(){
        PureTimeline timelineBSAG = new PureTimeline();
        PureTimelineItem item1876 = new PureTimelineItem("1876", new Paragraph("Betrieb der Aktiengesellschaft Bremer Pferdebahn wurde aufgenommen"));
        PureTimelineItem item1889 = new PureTimelineItem("1889", new Paragraph("Erste elektrische Versuchsstrecke wurde geplant"));
        PureTimelineItem item1892 = new PureTimelineItem("1892", new Paragraph("Bremer Pferdebahn nannte sich um in Bremer Straßenbahn und begann mit dem elektrischen Betrieb"));
        PureTimelineItem item1910 = new PureTimelineItem("1910", new Paragraph("Der erste Oberleitungsbus wurde in Betrieb genommen"));
        PureTimelineItem item1924 = new PureTimelineItem("1924", new Paragraph("Die erste Linie des Diesel-Omnibusnetzes der Bremer Vorortbahnen GmbH entstand"));
        PureTimelineItem item1953 = new PureTimelineItem("1953", new Paragraph("Straßenbahn übernimmt den Linienbusverkehr der BVG"));
        PureTimelineItem item1961 = new PureTimelineItem("1961", new Paragraph("Letzte Oberleitungs-Buslinie wird eingestellt"));
        timelineBSAG.add(item1876, item1889, item1889, item1892, item1910, item1924, item1953, item1961);

        timelineBSAG.setId("timelineBSAG");
        item1876.setId("item1876");
        item1961.setId("item1961");

        this.add(timelineBSAG);
    }

}