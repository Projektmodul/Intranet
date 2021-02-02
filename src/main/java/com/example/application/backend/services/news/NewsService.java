package com.example.application.backend.services.news;

import com.example.application.backend.entities.NewsEntity;
import com.example.application.backend.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private  final NewsRepository newsRepository;

    @Autowired
    public  NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public NewsEntity findById(int newsId){
        return getNewsRepository().findByNewsId(newsId);
    }

    public NewsRepository getNewsRepository(){
        return newsRepository;
    }

    public void save(NewsEntity newsEntity){
        getNewsRepository().saveAndFlush(newsEntity);
    }
}
