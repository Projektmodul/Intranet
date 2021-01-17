package com.example.application.ui;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;

@CssImport("./styles/views/main/mainView.css")
public class ContentHolder extends HorizontalLayout implements RouterLayout {



    private final SideBar sideBar;


    public ContentHolder() {

        setId("contentHolder");
        this.sideBar = new SideBar();
        add(sideBar);





    }

    public SideBar getSideBar() {
        return sideBar;
    }
}
