package com.example.application.ui.horizontal.community;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "noticeBoard", layout = ContentHolder.class)
@PageTitle("Schwarzes Brett")
public class NoticeBoardView extends Div {

    public NoticeBoardView() {
        setId("noticeBoard-view");
        setClassName("pageContentPosition");
        add(new Text("Schwarzes Brett"));
    }

}
