package com.example.application.backend.repositories;

import com.example.application.backend.entities.IdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<IdeaEntity, Integer> {
}
