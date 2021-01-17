package com.example.application.ui.horizontal.community;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  IdeaManagement View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "ideasManagement", layout = MainView.class)
@PageTitle("Ideenmanagement")
public class IdeasManagementView extends Div {

    public IdeasManagementView() {
        setId("ideasManagement");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");
        add(new Text("Ideenmanagement"));
    }

}
