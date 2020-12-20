package com.example.application.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H5;

/**
 * This is a basic notification class. Title, description and date are the attributes, they are
 * added to a Details component for display.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   20-12-2020
 */


public class Notification {
    private String title;
    private String description;
    private String date; //this could be later changed into a Date object instead of a string
    private Details details;

    public Notification(String title, String description, String date){
        this.title = title;
        this.description = description;
        this.date = date;

        details = new Details();
        details.setSummaryText(this.title);
        details.addContent(new H5(this.description),new Text(this.date));
        details.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.REVERSE);

    }

    public Details getDetails() {
        return details;
    }

}
