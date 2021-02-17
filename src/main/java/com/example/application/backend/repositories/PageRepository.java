package com.example.application.backend.repositories;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This interface is a repository for the PageEntity.
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   04.01.2021
 * @lastUpdated 25.01.2021
 */
public interface PageRepository extends JpaRepository<PageEntity, Integer> {
    PageEntity findByPageId(int pageId);

    @Query("SELECT p FROM pages p WHERE p.title = ?1 and p.user = ?2")
    PageEntity findPageByTitleAndUsername(String title, UserEntity userEntity);


}
