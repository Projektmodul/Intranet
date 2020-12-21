package com.example.application.ui.vertical.calendar;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "calendar", layout = ContentHolder.class)
@PageTitle("Kalendar")
public class CalendarView extends Div {

    public CalendarView() {
        setId("calendar-view");
        setClassName("pageContentPosition");
        add(new Text("Das ist der Test von Laura :D"));
    }

}
