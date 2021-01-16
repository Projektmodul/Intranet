package com.example.application.ui.horizontal.library;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  MediaView shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "media", layout = MainView.class)
@PageTitle("Medien")
public class MediaView extends Div {

    public MediaView() {
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");
        add(new Text("Medien"));
    }

}
