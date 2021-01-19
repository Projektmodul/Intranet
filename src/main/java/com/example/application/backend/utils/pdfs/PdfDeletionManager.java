package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.services.files.DocumentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * This is class that takes care of deleting documents on the
 * database and on the server.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 1.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
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
        if(deleteFile()){
            System.out.println("Deletion of file from Server successful!");
        }else{
            System.out.println("Deletion failed!");
        }
    }

    public void deleteFromDatabase(){
        try{
            this.documentService.delete(documentEntity);
        }catch(NullPointerException e)
        {
            System.out.println("No such file exists.");
        }
    }

    public boolean deleteFile(){
        try{
            return Files.deleteIfExists(
                    Paths.get(documentEntity.getPath()));
        }
        catch(NoSuchFileException e)
        {
            System.out.println("No such file exists.");
            return false;
        }
        catch(IOException e)
        {
            System.out.println("Invalid File.");
            return false;
        }
    }
}
