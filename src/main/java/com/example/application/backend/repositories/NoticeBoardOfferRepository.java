/**
 *  Home View shows ...
 *
 *  @author Monika Martius, Jessica Reistel
 *  @version 1.0
 *  @since 23.01.2021
 *  @lastUpdated 23.01.2021
 */
package com.example.application.backend.repositories;

import com.example.application.backend.entities.NoticeBoardOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardOfferRepository extends JpaRepository<NoticeBoardOfferEntity, Integer> {
    NoticeBoardOfferEntity findByNoticeBoardOfferId(int noticeBoardOfferId);

}
