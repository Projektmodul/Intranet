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
        PureTimeline timeline = new PureTimeline();
        PureTimelineItem item1 = new PureTimelineItem("1976", new Paragraph("Betrieb der Aktiengesellschaft Bremer Pferdebahn wurde aufgenommen"));
        PureTimelineItem item2 = new PureTimelineItem("1889", new Paragraph("Erste elektrische Versuchsstrecke wurde geplant"));
        PureTimelineItem item3 = new PureTimelineItem("1892", new Paragraph("Bremer Pferdebahn nannte sich um in Bremer Straßenbahn und begann mit dem elektrischen Betrieb"));
        PureTimelineItem item4 = new PureTimelineItem("1910", new Paragraph("Der erste Oberleitungsbus wurde in Betrieb genommen"));
        PureTimelineItem item5 = new PureTimelineItem("1924", new Paragraph("Die erste Linie des Diesel-Omnibusnetzes der Bremer Vorortbahnen GmbH entstand"));
        PureTimelineItem item6 = new PureTimelineItem("1953", new Paragraph("Straßenbahn übernimmt den Linienbusverkehr der BVG"));
        PureTimelineItem item7 = new PureTimelineItem("1961", new Paragraph("Letzte Oberleitungs-Buslinie wird eingestellt"));
        timeline.add(item1, item2, item2, item3, item4, item5, item6, item7);

        timeline.setId("timeline");
        item1.setId("item1");
        item7.setId("item7");

        this.add(timeline);
    }

}