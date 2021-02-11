package com.example.application.backend.services.files;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a service class for the entity document.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 2.0
 * @since   19.01.2021
 * @lastUpdated 19.01.2021
 */
@Service
public class DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    DocumentService(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    public DocumentRepository getDocumentRepository(){return documentRepository; }

    public void save (DocumentEntity documentEntity){
        getDocumentRepository().saveAndFlush(documentEntity);
    }

    public DocumentEntity findDocumentById(int documentId){
        return getDocumentRepository().findByDocumentId(documentId);
    }

    public void delete (DocumentEntity documentEntity){
        getDocumentRepository().delete(documentEntity);
    }
}
