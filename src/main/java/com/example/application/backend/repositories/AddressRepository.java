package com.example.application.backend.repositories;

import com.example.application.backend.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the AddressEntity.
 * @author Sabrine Gamdou
 * @version 2.0
 * @since 05.01.2021
 * @lastUpdatet 12.01.2021 by Jessica Reistel
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
