package com.example.application.ui;
import com.example.application.backend.entities.NewsEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.horizontal.ourCompany.news.NewsArticle;
import com.flowingcode.vaadin.addons.rssitems.RssItems;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;


/**
 *  Home View shows ...
 *
 *  @author Vanessa Skowronsky, Monika Martius, Laura Neuendorf, Jessica Reistel
 *  @version 3.0
 *  @since 04.01.2021
 *  @lastUpdated 20.01.2021
 */
@RouteAlias(value = "", layout = MainView.class)
@Route(value = "home", layout = MainView.class)
@PageTitle("BSAG Intranet")

public class HomeView extends Div {

    private UserService userService;
    private UserEntity userEntity;
    private PageService pageService;
    private H1 pageTitle;
    private PageEntity pageEntity;
    private GetUserController getUserController;
    private String username;

    private Div newsContainer;
    private List<NewsArticle> newsArticles;

    public HomeView(UserService userService, PageService pageService) {
        this.userService = userService;
        this.pageService = pageService;
        getUserController = new GetUserController();

        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        setData();

        newsArticles = new ArrayList<>();

        initializeNewsArticles();
        initializeNewsContainer();

        add(newsContainer);
    }

    private void setData(){
        username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        pageEntity = pageService.findPageById(1);
        pageTitle = new H1(pageEntity.getTitle() + " " + userEntity.getFirstName() +" " + userEntity.getSurname() );
        this.add(pageTitle);

        RssItems faz = new RssItems("https://www.faz.net/rss/aktuell/", 6, 100, 100, true, "description");
        add(faz);
    }

    public void initializeNewsContainer(){
        newsContainer = new Div();
        newsContainer.setId("newsContainer");

        for(NewsArticle newsArticle : newsArticles) newsContainer.add(newsArticle);

    }

    public void initializeNewsArticles(){
        List<NewsEntity> newsEntities = pageEntity.getNews();
        for(NewsEntity newsEntity : newsEntities){
            newsArticles.add(new NewsArticle(newsEntity.getImage(), newsEntity));
        }
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }
}