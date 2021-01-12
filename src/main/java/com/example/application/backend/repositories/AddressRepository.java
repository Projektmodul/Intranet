package com.example.application.backend.repositories;

import com.example.application.backend.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    AddressEntity findByAddressId(int addressId);
}
