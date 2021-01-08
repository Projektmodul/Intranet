package com.example.application.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainView.class)
@CssImport("./styles/views/main/mainView.css")
public class ContentHolder extends HorizontalLayout implements RouterLayout {

    public ContentHolder() {

        setId("contentHolder");
        SideBar sideBar = new SideBar();
        sideBar.setClassName("layoutSidebar");
        add(sideBar);

    }

}
