package com.example.application.backend.repositories;

import com.example.application.backend.entities.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the PageEntity.
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   04.01.2021
 * @lastUpdated 25.01.2021
 */
public interface PageRepository extends JpaRepository<PageEntity, Integer> {
    PageEntity findByPageId(int pageId);
}
