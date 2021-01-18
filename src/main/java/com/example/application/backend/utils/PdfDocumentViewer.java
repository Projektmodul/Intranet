package com.example.application.backend.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

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