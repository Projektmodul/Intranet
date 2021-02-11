package com.example.application.backend.repositories;

import com.example.application.backend.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the NewsEntity.
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   04.01.2021
 * @lastUpdated 25.01.2021
 */
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    NewsEntity findByNewsId(int newsId);
}
