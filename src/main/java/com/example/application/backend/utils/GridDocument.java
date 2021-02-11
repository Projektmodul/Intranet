package com.example.application.backend.utils;

import com.example.application.backend.entities.DocumentEntity;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * GridDocument class is a POJO (plain old java object) that has filename, keyword and an anchor link as attributes.
 *
 * @author Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since 24.01.2021
 * @lastUpdated 24.01.2021 from Sabrine Gamdou, Anastasiya Jackwerth
 */
public class GridDocument {

    private String filename;
    private String keyword;
    private Anchor downloadLink;

    public GridDocument(String filename, String keyword, Anchor downloadLink) {
        this.filename = filename;
        this.keyword = keyword;
        this.downloadLink = downloadLink;
    }

    public String getKeyword() {
        return keyword;
    }

    public Anchor getDownloadLink() {
        downloadLink.setTarget("_blank");
        return downloadLink;
    }

    /**
     * DocumentEntitiesConverter is an embedded static helper class with a static method to convert a documentsList to a
     * gridDocumentsList and a static method to create an anchor link fromm a filepath.
     *
     * @author Sabrine Gamdou, Anastasiya Jackwerth
     * @version 1.0
     * @lastUpdated 24.01.2021 from Sabrine Gamdou, Anastasiya Jackwerth
     * @since 24.01.2021
     */
    public static class DocumentEntitiesConverter {
        public static Anchor createDownloadLink(String path, String filename) {

            return new Anchor(new StreamResource(filename, () -> {
                try{
                    return new FileInputStream(path);
                } catch (Exception e){
                    return new ByteArrayInputStream(new byte[]{});
                }
            }), filename);
        }

        public static List<GridDocument> convertDocumentEntities(List<DocumentEntity> documentEntities){
            List<GridDocument> gridDocuments = new ArrayList<>();
            for (DocumentEntity documentEntity : documentEntities){
                GridDocument gridDocument = new GridDocument(documentEntity.getFileName(),
                        documentEntity.getKeyword(),
                        createDownloadLink(documentEntity.getPath(),
                                documentEntity.getFileName()));
                gridDocuments.add(gridDocument);
            }
            return gridDocuments;
        }
    }
}