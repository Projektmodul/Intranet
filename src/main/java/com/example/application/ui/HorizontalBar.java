//created @ Monika Martius
package com.example.application.ui;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;

@CssImport("./styles/views/main/horizontalBar.css")


public class HorizontalBar extends HorizontalLayout {

    Label welcome;
    Label centers;
    Label projects;
    Label library;
    Label services;
    Label community;

    public HorizontalBar() {

        //Text selected = new Text("");
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);
        menuBar.setClassName("menuBarStyleOne");

        /*
         *  ourCompany
         *              */
        //creating icons
        Icon handshake = new Icon(VaadinIcon.HANDSHAKE);
        handshake.setClassName("icons");
        handshake.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("welcome")));

        Icon home = new Icon(VaadinIcon.HOME);
        home.setSize("35px");
        home.setColor("#FFFFFF");
        home.addClickListener( e-> home.getUI().ifPresent(ui -> ui.navigate("")));
        menuBar.addItem(home);
        //space *wink*
        menuBar.addItem("").setEnabled(false);

        Icon info = new Icon(VaadinIcon.INFO);
        info.setClassName("icons");
        info.addClickListener( e-> info.getUI().ifPresent(ui -> ui.navigate("aboutUs")));

        Icon news = new Icon(VaadinIcon.NEWSPAPER);
        news.setClassName("icons");
        news.addClickListener( e-> news.getUI().ifPresent(ui -> ui.navigate("news")));

        Icon sport = new Icon(VaadinIcon.EXIT);
        sport.setClassName("icons");
        sport.addClickListener( e-> news.getUI().ifPresent(ui -> ui.navigate("sport")));

        Icon jobs = new Icon(VaadinIcon.DOLLAR);
        jobs.setClassName("icons");
        jobs.addClickListener( e-> news.getUI().ifPresent(ui -> ui.navigate("career")));

        //create Spans
        Span welcomeText = new Span("Willkommen");
        welcomeText.setClassName("spanStyle");

        Span aboutUsText = new Span("Über uns");
        aboutUsText.setClassName("spanStyle");

        Span newsText = new Span("Nachrichten");
        newsText.setClassName("spanStyle");

        Span sportText = new Span("Sport&Freizeit");
        sportText.setClassName("spanStyle");

        Span jobText = new Span("Stellenangebote");
        jobText.setClassName("spanStyle");

        //create welcome_tab_submenu
        Tab welcomeTab = new Tab(handshake);
        Tab infoTab = new Tab(info);
        Tab newsTab = new Tab(news);
        Tab sportTab = new Tab(sport);
        Tab jobTab = new Tab(jobs);

        //welcomeLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout layoutWelcome = new VerticalLayout(welcomeText, welcomeTab);
        layoutWelcome.setClassName("layoutVerti");
        layoutWelcome.getStyle().set("background-color", "#A00505");
        layoutWelcome.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutAboutUs = new VerticalLayout(aboutUsText, infoTab);
        layoutAboutUs.setClassName("layoutVerti");
        layoutAboutUs.getStyle().set("background-color", "#A00505");
        layoutAboutUs.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutNews = new VerticalLayout(newsText, newsTab);
        layoutNews.setClassName("layoutVerti");
        layoutNews.getStyle().set("background-color", "#A00505");
        layoutNews.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutSport = new VerticalLayout(sportText, sportTab);
        layoutSport.setClassName("layoutVerti");
        layoutSport.getStyle().set("background-color", "#A00505");
        layoutSport.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutJobs = new VerticalLayout(jobText, jobTab);
        layoutJobs.setClassName("layoutVerti");
        layoutJobs.getStyle().set("background-color", "#A00505");
        layoutJobs.setAlignItems(Alignment.CENTER);

        //insert ourCompanyBars into horizontal Layout
        HorizontalLayout hlOurCompany = new HorizontalLayout();
        hlOurCompany.add(layoutWelcome,layoutAboutUs,layoutNews,layoutSport,layoutJobs);
        // shorthand methods for changing the component theme variants
        hlOurCompany.setSpacing(true);

        //creating menuBar_welcome
        welcome = new Label("Unser Unternehmen");
        welcome.getStyle().set("color","white");
        MenuItem ourCompany = menuBar.addItem(welcome);
        //space *wink*
        menuBar.addItem("").setEnabled(false);
        ourCompany.getSubMenu().addItem(hlOurCompany);

        /*
         * Centers
         *          */
        //creating icons
        Icon centerI = new Icon(VaadinIcon.CLUSTER);
        centerI.setClassName("icons");
        centerI.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("centerI")));

        //create spans
        Span centerIText = new Span("Center I");
        centerIText.setClassName("spanStyle");

        //create centerTabSubmenu
        Tab centerITab = new Tab(centerI);

        //welcomeLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout layoutCenterI = new VerticalLayout(centerIText, centerITab);
        layoutCenterI.setClassName("layoutVerti");
        layoutCenterI.getStyle().set("background-color", "#0A5396");
        layoutCenterI.setAlignItems(Alignment.CENTER);

        //creating menuBarCenters
        centers = new Label("Centers");
        centers.getStyle().set("color","white");
        MenuItem center = menuBar.addItem(centers);
        //space *wink*
        menuBar.addItem("").setEnabled(false);
        center.getSubMenu().addItem(layoutCenterI);

        /*
         * Projects
         *          */
        //creating icons
        Icon nordlight = new Icon(VaadinIcon.TRAIN);
        nordlight.setClassName("icons");
        nordlight.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("nordlicht")));

        //create spans
        Span nordlightText = new Span("Nordlicht");
        nordlightText.setClassName("spanStyle");

        //create projectsLayoutSubmenu
        Tab nordlightTab = new Tab(nordlight);

        //projectLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout layoutNordlight = new VerticalLayout(nordlightText, nordlightTab);
        layoutNordlight.setClassName("layoutVerti");
        layoutNordlight.getStyle().set("background-color", "#581092");
        layoutNordlight.setAlignItems(Alignment.CENTER);

        //creating menuBarProjects
        projects = new Label("Projekte");
        projects.getStyle().set("color","white");
        MenuItem project = menuBar.addItem(projects);
        //space *wink*
        menuBar.addItem("").setEnabled(false);
        project.getSubMenu().addItem(layoutNordlight);

        /*
         * Library
         *          */
        //creation icons
        Icon documents = new Icon(VaadinIcon.CLIPBOARD_TEXT);
        documents.setClassName("icons");
        documents.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("documents")));

        Icon wiki = new Icon(VaadinIcon.VIMEO);
        wiki.setClassName("icons");
        wiki.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("wiki")));
        //Image wiki = new Image("images/wikiU.png", "My Project logo");
        //wiki.setClassName("image");
        //wiki.getStyle().set("color","white");

        Icon archive = new Icon(VaadinIcon.ARCHIVE);
        archive.setClassName("icons");
        archive.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("archive")));

        Icon media = new Icon(VaadinIcon.FILM);
        media.setClassName("icons");
        media.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("media")));

        Icon faq = new Icon(VaadinIcon.QUESTION);
        faq.setClassName("icons");
        faq.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("fAQ")));

        //create spans
        Span documentsText = new Span("Unterlagen");
        documentsText.setClassName("spanStyle");

        Span wikiText = new Span("Wiki");
        wikiText.setClassName("spanStyle");

        Span archiveText = new Span("Archive");
        archiveText.setClassName("spanStyle");

        Span mediaText = new Span("Media");
        mediaText.setClassName("spanStyle");

        Span faqText = new Span("FAQ");
        faqText.setClassName("spanStyle");

        //create libraryTabSubmenue
        Tab docuTab = new Tab(documents);
        Tab wikiTab = new Tab(wiki);
        Tab archiTab = new Tab(archive);
        Tab mediTab = new Tab(media);
        Tab faqTab = new Tab(faq);

        //docuLayout.setAlginItems(Aligment.CENTER)
        VerticalLayout layoutDocu = new VerticalLayout(documentsText,docuTab);
        layoutDocu.setClassName("layoutVerti");
        layoutDocu.getStyle().set("background-color", "#2F7C78");
        layoutDocu.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutWiki = new VerticalLayout(wikiText,wikiTab);
        layoutWiki.setClassName("layoutVerti");
        layoutWiki.getStyle().set("background-color", "#2F7C78");
        layoutWiki.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutArchi = new VerticalLayout(archiveText,archiTab);
        layoutArchi.setClassName("layoutVerti");
        layoutArchi.getStyle().set("background-color", "#2F7C78");
        layoutArchi.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutMedia = new VerticalLayout(mediaText,mediTab);
        layoutMedia.setClassName("layoutVerti");
        layoutMedia.getStyle().set("background-color", "#2F7C78");
        layoutMedia.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutFAQ = new VerticalLayout(faqText,faqTab);
        layoutFAQ.setClassName("layoutVerti");
        layoutFAQ.getStyle().set("background-color", "#2F7C78");
        layoutFAQ.setAlignItems(Alignment.CENTER);

        //insert ourLibraryBars into horizontal Layout
        HorizontalLayout hlLibrary = new HorizontalLayout();
        hlLibrary.add(layoutDocu,layoutWiki,layoutArchi,layoutMedia,layoutFAQ);
        // shorthand methods for changing the component theme variants
        hlLibrary.setSpacing(true);

        //creating menuBarLibrary
        library = new Label("Bibliothek");
        library.getStyle().set("color","white");
        MenuItem lib = menuBar.addItem(library);
        //space *wink*
        menuBar.addItem("").setEnabled(false);
        lib.getSubMenu().addItem(hlLibrary);

        /*
         * Services
         *          */
        //creation icons
        Icon time = new Icon(VaadinIcon.CHART_TIMELINE);
        time.setClassName("icons");

        Icon lsa = new Icon(VaadinIcon.PYRAMID_CHART);
        lsa.setClassName("icons");

        Icon drive = new Icon(VaadinIcon.CAR);
        drive.setClassName("icons");

        Icon journey = new Icon(VaadinIcon.FLIGHT_TAKEOFF);
        journey.setClassName("icons");
        journey.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("dienstreisen")));

        //create spans
        Span timeText = new Span("Zeitkonto");
        timeText.setClassName("spanStyle");

        Span lsaText = new Span("LSA-Meldungen");
        lsaText.setClassName("spanStyle");

        Span driveText = new Span("Fahrdienst");
        driveText.setClassName("spanStyle");

        Span journeyText = new Span("Dienstreisen");
        journeyText.setClassName("spanStyle");

        //create libraryTabSubmenue
        Tab timeTab = new Tab(time);
        Tab lsaTab = new Tab(lsa);
        Tab driveTab = new Tab(drive);
        Tab journeyTab = new Tab(journey);

        //serviceLayout.setAlginItems(Aligment.CENTER)
        VerticalLayout layoutTime = new VerticalLayout(timeText,timeTab);
        layoutTime.setClassName("layoutVerti");
        layoutTime.getStyle().set("background-color", "#FF5621");
        layoutTime.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutLSA = new VerticalLayout(lsaText,lsaTab);
        layoutLSA.setClassName("layoutVerti");
        layoutLSA.getStyle().set("background-color", "#FF5621");
        layoutLSA.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutDrive = new VerticalLayout(driveText,driveTab);
        layoutDrive.setClassName("layoutVerti");
        layoutDrive.getStyle().set("background-color", "#FF5621");
        layoutDrive.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutJourney = new VerticalLayout(journeyText,journeyTab);
        layoutJourney.setClassName("layoutVerti");
        layoutJourney.getStyle().set("background-color", "#FF5621");
        layoutJourney.setAlignItems(Alignment.CENTER);

        //insert ourLibraryBars into horizontal Layout
        HorizontalLayout hlServices = new HorizontalLayout();
        hlServices.add(layoutTime,layoutLSA,layoutDrive,layoutJourney);
        // shorthand methods for changing the component theme variants
        hlServices.setSpacing(true);

        //creating menuBarLibrary
        services = new Label("Services");
        services.getStyle().set("color","white");
        MenuItem service = menuBar.addItem(services);
        //space *wink*
        menuBar.addItem("").setEnabled(false);
        service.getSubMenu().addItem(hlServices);

        /*
         * Community
         *          */
        //creation icons
        Icon blog = new Icon(VaadinIcon.PENCIL);
        blog.setClassName("icons");
        blog.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("blog")));

        Icon board = new Icon(VaadinIcon.CLIPBOARD_TEXT);
        board.setClassName("icons");
        board.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("noticeBoard")));

        Icon idea = new Icon(VaadinIcon.HANDS_UP);
        idea.setClassName("icons");
        idea.addClickListener( e-> handshake.getUI().ifPresent(ui -> ui.navigate("ideasManagement")));

        //create spans
        Span blogText = new Span("Blog");
        blogText.setClassName("spanStyle");

        Span boardText = new Span("SchwarzesBrett");
        boardText.setClassName("spanStyle");

        Span ideaText = new Span("Ideenmanagement");
        ideaText.setClassName("spanStyle");

        //create serviceTabSubmenue
        Tab blogTab = new Tab(blog);
        Tab boardTab = new Tab(board);
        Tab ideaTab = new Tab(idea);

        //docuLayout.setAlginItems(Aligment.CENTER)
        VerticalLayout layoutBlog = new VerticalLayout(blogText,blogTab);
        layoutBlog.setClassName("layoutVerti");
        layoutBlog.getStyle().set("background-color", "#F0D12C");
        layoutBlog.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutBoard = new VerticalLayout(boardText,boardTab);
        layoutBoard.setClassName("layoutVerti");
        layoutBoard.getStyle().set("background-color", "#F0D12C");
        layoutBoard.setAlignItems(Alignment.CENTER);

        VerticalLayout layoutIdea = new VerticalLayout(ideaText,ideaTab);
        layoutIdea.setClassName("layoutVerti");
        layoutIdea.getStyle().set("background-color", "#F0D12C");
        layoutIdea.setAlignItems(Alignment.CENTER);

        //insert communityBar into horizontal Layout
        HorizontalLayout hlCommunity = new HorizontalLayout();
        hlCommunity.add(layoutBlog,layoutBoard,layoutIdea);
        // shorthand methods for changing the component theme variants
        hlCommunity.setSpacing(true);

        //creating menuBarLibrary
        community = new Label("Community");
        community.getStyle().set("color","white");
        MenuItem commun = menuBar.addItem(community);
        commun.getSubMenu().addItem(hlCommunity);

        add(menuBar);
    }
}
