package com.example.application.backend.repositories;

import com.example.application.backend.entities.NoticeBoardOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the NoticeBoardOfferEntity.
 * @author Sabrine Gamdou
 * @version 2.0
 * @since 05.01.2021
 * @lastUpdatet 24.01.2021 by Jessica Reistel and Monika Martius
 */
public interface NoticeBoardOfferRepository extends JpaRepository<NoticeBoardOfferEntity, Integer> {
    NoticeBoardOfferEntity findByNoticeBoardOfferId(int noticeBoardOfferId);

}
