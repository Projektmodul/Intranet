package com.example.application.ui.horizontal.library;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "media", layout = MainView.class)
@PageTitle("Medien")
public class MediaView extends Div {

    public MediaView() {
        setId("media-view");
        add(new Text("Medien"));
    }

}
