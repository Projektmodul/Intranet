package com.example.application.backend.utils.interfaces;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.PageEntity;
import com.vaadin.flow.component.Component;

import java.io.IOException;
import java.io.InputStream;

public interface Convertible<T> {

    //Component createFileFromStream(InputStream stream, byte[] fileAsBytes, String fileName);
   // Component createViewerForFileFromServer(T file);
    boolean generateFileFromByteStream(byte[] fileContent, String fileName)  throws IOException;
    byte[] getArrayFromInputStream(InputStream inputStream) throws IOException;
    String changeGlobalFileName(String fileName);
    PageEntity getPage();
    void setPage(PageEntity page);

}
