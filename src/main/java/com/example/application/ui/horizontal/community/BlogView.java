package com.example.application.ui.horizontal.community;

import com.example.application.ui.vertical.search.SearchView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.example.application.ui.MainView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
