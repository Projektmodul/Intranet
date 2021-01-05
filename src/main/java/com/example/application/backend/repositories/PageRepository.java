package com.example.application.backend.repositories;

import com.example.application.backend.entities.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<PageEntity, Integer> {
}
