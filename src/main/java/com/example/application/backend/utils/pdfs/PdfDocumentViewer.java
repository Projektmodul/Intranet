package com.example.application.backend.utils.pdfs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

/**
 * A UI-Component that prints a streamResource (pdf file in this case) to the
 *  screen in a proper pdf viewer/container.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   18.01.2021
 * @lastUpdated  18.01.2021
 */

@Tag("object")
public class PdfDocumentViewer extends Component implements HasSize {

    public PdfDocumentViewer(StreamResource resource) {
        this();
        getElement().setAttribute("data", resource);
    }

    protected PdfDocumentViewer() {
        getElement().setAttribute("type", "application/pdf");
        setSizeFull();
    }
}