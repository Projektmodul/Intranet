package com.example.application.ui;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.flowingcode.vaadin.addons.rssitems.RssItems;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 *  Home View shows the personal greeting of an employee. In additional, news is displayed via an RSS feed
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
    private PageService pageService;
    private GetUserController getUserController;

    public HomeView(UserService userService, PageService pageService) {
        this.userService = userService;
        this.pageService = pageService;
        getUserController = new GetUserController();

        setId("home");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        setContent();
    }

    /**
     * This method fetches the data from the database
     * and displays it on the corresponding page
     */
    private void setContent(){
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        PageEntity pageEntity = pageService.findPageById(1);
        H1 pageTitle = new H1(pageEntity.getTitle() + " " + userEntity.getFirstName() + " " + userEntity.getSurname());
        this.add(pageTitle);

        RssItems faz = new RssItems("https://www.faz.net/rss/aktuell/", 6, 100, 100, true, "description");
        add(faz);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }
}