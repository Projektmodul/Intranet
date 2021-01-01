package com.example.application.ui.vertical.timetable;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "https://fahrplaner.vbn.de", layout = ContentHolder.class)
@PageTitle("Fahrplan")
public class TimetableView extends Div {

    public TimetableView() {
        setId("timetable-view");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
    }

}
