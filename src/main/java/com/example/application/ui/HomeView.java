package com.example.application.ui;
import com.example.application.backend.entities.NewsEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.news.NewsService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.horizontal.ourCompany.news.NewsArticle;
import com.flowingcode.vaadin.addons.rssitems.RssItems;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;


/**
 *  Home View shows the personal greeting of an employee. In additional, news is displayed via an RSS feed
 *
 *  @author Vanessa Skowronsky, Monika Martius, Laura Neuendorf, Jessica Reistel,
 *          Anastasiya Jackwerth and Sabrine Gamdou
 *  @version 6.0
 *  @since 04.01.2021
 *  @lastUpdated 07.02.2021 by Jessica Reistel
 */
@RouteAlias(value = "", layout = MainView.class)
@Route(value = "home", layout = MainView.class)
@PageTitle("BSAG Intranet")

public class HomeView extends Div {
    private final UserService userService;

    private final PageService pageService;
    private final ImageService imageService;
    private final NewsService newsService;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private final GetUserController getUserController;

    private RssItems faz;

    private Div newsContainer;
    private final List<NewsArticle> newsArticles;

    public HomeView(UserService userService, PageService pageService,
                    ImageService imageService, NewsService newsService) {
        this.userService = userService;
        this.pageService = pageService;
        this.imageService = imageService;
        this.newsService = newsService;
        getUserController = new GetUserController();

        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        setContent();

        newsArticles = new ArrayList<>();

        initializeNewsArticles();
        initializeNewsContainer();

        add(newsContainer, faz);
    }

    /**
     * This method fetches the data from the database and displays it on the corresponding page
     */
    private void setContent(){
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        pageEntity = pageService.findPageById(1);
        H1 pageTitle = new H1(pageEntity.getTitle() + " " + userEntity.getFirstName() +" " + userEntity.getSurname() );
        Paragraph pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle, pageContent);

        faz = new RssItems("https://www.faz.net/rss/aktuell/", 6, 100, 100, true, "description");
    }

    public void initializeNewsContainer(){
        newsContainer = new Div();
        newsContainer.setId("newsContainer");

        for(NewsArticle newsArticle : newsArticles) newsContainer.add(newsArticle);
    }

    public void initializeNewsArticles(){
        List<NewsEntity> newsEntities = pageEntity.getNews();

        for(int i = newsEntities.size() - 1 ; i>=0 ; i--){
            newsArticles.add(new NewsArticle(newsEntities.get(i).getImage(), newsEntities.get(i), imageService,
                    newsService, userEntity.getRole().getRoleId()));
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }
}