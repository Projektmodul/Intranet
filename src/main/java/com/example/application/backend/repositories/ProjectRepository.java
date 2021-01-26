package com.example.application.backend.repositories;

import com.example.application.backend.entities.NoticeBoardOfferEntity;
import com.example.application.backend.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<NoticeBoardOfferEntity, Integer> {
    ProjectEntity findByProjectId(int projectId);
}
