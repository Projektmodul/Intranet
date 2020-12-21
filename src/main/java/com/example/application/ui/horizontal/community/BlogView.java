package com.example.application.ui.horizontal.community;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;


@Route(value = "blog", layout = MainView.class)
@PageTitle("Blog")
public class BlogView extends Div {

    public BlogView() {
        setId("blog-view");
        setClassName("pageContentPosition");

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(new Anchor("https://blog.bsag.de/", "Blog"));
        add(verticalLayout);

    }

}
