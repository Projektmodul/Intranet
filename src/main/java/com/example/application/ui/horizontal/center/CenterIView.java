package com.example.application.ui.horizontal.center;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  CenterI View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "centerI", layout = MainView.class)
@PageTitle("CenterI")
public class CenterIView extends Div {

    public CenterIView() {
        setClassName("pageContentPosition");
        addClassName("centerColorscheme");
        add(new Text("Center I"));

    }

}
