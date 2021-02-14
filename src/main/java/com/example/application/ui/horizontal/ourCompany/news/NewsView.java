package com.example.application.ui.horizontal.ourCompany.news;

import com.example.application.backend.entities.NewsEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.news.NewsService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.roles.RoleService;
import com.example.application.backend.services.users.UserService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

/**
 *  The news view shows all added company news
 *
 *  @author Anastasiya Jackwerth, Sabrine Gamdou
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
@Route(value = "news", layout = MainView.class)
@PageTitle("Nachrichten")
public class NewsView extends Div {

    private final PageService pageService;
    private UserService userService;
    private final ImageService imageService;
    private final NewsService newsService;

    private final PageEntity pageEntity;
    private final UserEntity userEntity;

    private Button addNews;
    private Div newsContainer;
    private final List<NewsArticle> newsArticles;

    public NewsView(PageService pageService, UserService userService, ImageService imageService,
                    NewsService newsService, RoleService roleService) {

        this.pageService = pageService;
        this.imageService = imageService;
        this.newsService = newsService;

        setId("news");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        pageEntity = pageService.findPageById(6);
        H1 pageTitle = new H1(pageEntity.getTitle());
        Paragraph pageText = new Paragraph(pageEntity.getContent());
        newsArticles = new ArrayList<>();

        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        int role = roleEntity.getRoleId();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Unser Unternehmen"), new Breadcrumb(pageEntity.getTitle()));

        initializeNewsArticles();
        initializeNewsContainer();
        initializeAddNewsButton();

        add(breadcrumbs, pageTitle, pageText);
        if(role == 1){
            add(addNews);
        }
        add(newsContainer);
    }

    public void initializeNewsContainer(){
        newsContainer = new Div();
        newsContainer.setId("newsContainer");

        for(NewsArticle newsArticle : newsArticles) newsContainer.add(newsArticle);
    }

    public void initializeNewsArticles(){
        List<NewsEntity> newsEntities = pageEntity.getNews();

        for(int i = newsEntities.size()  - 1; i>=0 ; i--){
            newsArticles.add(new NewsArticle(newsEntities.get(i).getImage(), newsEntities.get(i),
                    imageService, newsService, userEntity.getRole().getRoleId()));
        }
    }

    public void initializeAddNewsButton(){
        addNews = new Button("Unternehmensnachricht erstellen", event -> {
            NewsCreator newsCreator = new NewsCreator(pageEntity, userEntity, imageService, newsService, pageService);
            newsCreator.open();
        });
        addNews.setId("addNews");
    }
}
