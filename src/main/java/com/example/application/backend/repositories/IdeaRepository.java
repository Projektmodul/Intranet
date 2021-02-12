package com.example.application.backend.repositories;

import com.example.application.backend.entities.IdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the IdeaEntity.
 * @author Sabrine Gamdou
 * @version 2.0
 * @since 05.01.2021
 * @lastUpdatet 25.01.2021 by Jessica Reistel
 */
public interface IdeaRepository extends JpaRepository<IdeaEntity, Integer> {
    IdeaEntity findByIdeaId(int ideaId);
}
