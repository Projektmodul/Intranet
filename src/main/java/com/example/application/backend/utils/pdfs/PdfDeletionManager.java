package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.services.files.DocumentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is class that takes care of deleting documents on the
 * database and on the server.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 2.0
 * @since   21.12.2020
 * @lastUpdated 04.02.2021 from by Sabrine Gamdou
 */
public class PdfDeletionManager {

    DocumentService documentService;

    private DocumentEntity documentEntity;

    public PdfDeletionManager (DocumentEntity documentEntity, DocumentService documentService){
        this.documentEntity = documentEntity;
        this.documentService = documentService;
    }

    public void delete(){
        deleteFromServer();
        deleteFromDatabase();
    }

    public void deleteFromServer(){
        deleteFile();
    }

    public void deleteFromDatabase(){
        try{
            this.documentService.delete(documentEntity);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public boolean deleteFile(){
        try{
            return Files.deleteIfExists(
                    Paths.get(documentEntity.getPath()));
        } catch(IOException e) { return false; }
    }
}
