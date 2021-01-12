package com.example.application.ui.vertical.canteen;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * This is a View for all menus.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since   14.12.2020
 * @lastUpdated 12.01.2021
 */

@Route(value = "canteen", layout = ContentHolder.class)
@PageTitle("Betriebsrestaurant")
@CssImport("./styles/views/main/canteen.css")
public class CanteenView extends Div {

    public CanteenView() {
        setId("canteen-view");
        setClassName("pageContentPosition");
        add(new Text("Ich habe hunger!!!"));
    }

}
