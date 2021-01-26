package com.example.application.backend.services.links;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkService {
    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository){
        this.linkRepository = linkRepository;
    }

    public LinkEntity findById(int linkId){
        return getLinkRepository().findByLinkId(linkId);
    }

    public LinkRepository getLinkRepository(){
        return linkRepository;
    }
}
