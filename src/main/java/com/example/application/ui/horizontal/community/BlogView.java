package com.example.application.ui.horizontal.community;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

<<<<<<< HEAD
@Route(value = "blog", layout = MainView.class)
=======
@Route(value = "Blog", layout = MainView.class)
>>>>>>> bd52951 (Added routes to the horizontal navbar)
@PageTitle("Blog")
public class BlogView extends Div {

    public BlogView() {
        setId("blog-view");
        setClassName("pageContentPosition");
        add(new Text("Blog"));
    }

}
