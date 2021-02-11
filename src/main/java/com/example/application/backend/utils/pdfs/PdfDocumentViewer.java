package com.example.application.backend.utils.pdfs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;
/**
 * This is a pdf viewer component.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 3.0
 * @since   21.12.2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */
@Tag("object")
public class PdfDocumentViewer extends Component implements HasSize {

    public PdfDocumentViewer(StreamResource resource){
        this();
        getElement().setAttribute("data", resource);
    }

    protected PdfDocumentViewer(){
        getElement().setAttribute("type", "application/pdf");
        setSizeFull();
    }
}
