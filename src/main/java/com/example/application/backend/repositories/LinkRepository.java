package com.example.application.backend.repositories;

import com.example.application.backend.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, Integer> {
    LinkEntity findByLinkId(int linkId);
}
