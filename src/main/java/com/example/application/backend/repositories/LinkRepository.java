package com.example.application.backend.repositories;

import com.example.application.backend.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the LinkEntity.
 * @author Sabrine Gamdou
 * @version 2.0
 * @since 05.01.2021
 * @lastUpdatet 26.01.2021 by Laura Neuendorf
 */
public interface LinkRepository extends JpaRepository<LinkEntity, Integer> {
    LinkEntity findByLinkId(int linkId);
}
