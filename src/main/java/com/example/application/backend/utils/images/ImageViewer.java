package com.example.application.backend.utils.images;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

/**
 * This is an image viewer component.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

@Tag("object")
public class ImageViewer extends Component implements HasSize {

    public ImageViewer(StreamResource resource) {
        this();
        getElement().setAttribute("data", resource);
    }

    protected ImageViewer() {
        getElement().setAttribute("type", "image/jpeg");
        setSizeFull();
    }


}