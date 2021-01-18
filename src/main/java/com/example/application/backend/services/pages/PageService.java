package com.example.application.backend.services.pages;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
