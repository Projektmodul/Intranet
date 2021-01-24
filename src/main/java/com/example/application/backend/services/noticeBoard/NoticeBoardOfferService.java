/**
 *  Home View shows ...
 *
 *  @author Monika Martius, Jessica Reistel
 *  @version 1.0
 *  @since 23.01.2021
 *  @lastUpdated 23.01.2021
 */

package com.example.application.backend.services.noticeBoard;
import com.example.application.backend.entities.NoticeBoardOfferEntity;
import com.example.application.backend.repositories.NoticeBoardOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
    public int findMaxId() {
        String sql = "SELECT max(notice_board_offer_id) from notice_board_offers";
        try {
            return jdbcTemplate.queryForObject(sql,Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }
}

