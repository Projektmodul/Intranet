package com.example.application.backend.repositories;

import com.example.application.backend.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the DocumentEntity.
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   04.01.2021
 * @lastUpdated 25.01.2021
 */
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
    DocumentEntity findByDocumentId(int documentId);
}
