package com.example.application.backend.repositories;

import com.example.application.backend.entities.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressesEntity, Integer> {
}
