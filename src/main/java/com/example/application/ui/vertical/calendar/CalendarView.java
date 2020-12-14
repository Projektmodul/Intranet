package com.example.application.ui.vertical.calendar;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "calendar", layout = MainView.class)
@PageTitle("Kalendar")
public class CalendarView extends Div {

    public CalendarView() {
        setId("calendar-view");
        add(new Text("Content placeholder"));
    }

}
