package com.example.application.backend.services.myProfile;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is service class for the class MyProfileView.
 *  The service layer processes requests from the UI layer.
 *
 * @author  Jessica Reistel and Laura Neuendorf
 * @version 2.0
 * @since   11.01.2021
 * @lastUpdated 12.01.2021
 */

@Service
public class MyProfileViewService {

    private final PageRepository pageRepository;

    @Autowired
    public MyProfileViewService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;

    }
    public PageEntity findPageById(int pageId) {
        return getPageRepository().findByPageId(pageId);
    }

    public PageRepository getPageRepository() {
        return pageRepository;
    }
}
