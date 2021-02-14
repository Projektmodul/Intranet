package com.example.application.backend.services.noticeBoard;

import com.example.application.backend.entities.NoticeBoardOfferEntity;
import com.example.application.backend.repositories.NoticeBoardOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * This class is service class for the entity notice board offer.
 * The service layer processes requests from the UI layer.
 *
 * @author  Monika Martius and Jessica Reistel
 * @version 1.0
 * @since   24.01.2021
 */
@Service
public class NoticeBoardOfferService {

    private final NoticeBoardOfferRepository noticeBoardOfferRepository;

    @Autowired
    public NoticeBoardOfferService(NoticeBoardOfferRepository noticeBoardOfferRepository) {
        this.noticeBoardOfferRepository = noticeBoardOfferRepository;
    }
    public NoticeBoardOfferEntity findById(int noticeBoardOfferId) {
        return getNoticeBoardOfferRepository().findByNoticeBoardOfferId(noticeBoardOfferId);
    }
    public NoticeBoardOfferRepository getNoticeBoardOfferRepository() {
        return noticeBoardOfferRepository;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * This method selects data from a table by using Spring JdbcTemplate.
     * The query searches for the highest notice board offer id.
     * @return int as max id or null
     */
    public int findMaxId() {
        String sql = "SELECT max(notice_board_offer_id) from notice_board_offers";
        try {
            return jdbcTemplate.queryForObject(sql,Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }
}

