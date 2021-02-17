package com.example.application.backend.services.pages;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service class for the PageEntities
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since 19.01.2021
 */
@Service
public class PageService {

    private final PageRepository pageRepository;

    @Autowired
    public PageService(PageRepository pageRepository){
        this.pageRepository = pageRepository;
    }

    public PageEntity findPageById(int pageId) {
        return getPageRepository().findByPageId(pageId);
    }

    public PageRepository getPageRepository() {
        return pageRepository;
    }

    public PageEntity findPageByTitleAndUsername(String title, UserEntity userEntity){
        return getPageRepository().findPageByTitleAndUsername(title, userEntity);
    }

}
