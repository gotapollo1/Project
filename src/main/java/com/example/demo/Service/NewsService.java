package com.example.demo.Service;

import com.example.demo.Model.News;
import com.example.demo.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;
    private List<News> newsList = new ArrayList<>();
    public News addNews(@RequestBody News news){
        newsList.add(news);
        return newsRepository.save(news);
    }
    public String deleteNews(Integer id){
        newsRepository.deleteById(id);
        return "Remove News " + id;
    }
    public News updateNews(Integer id,News news){
        News existingNews = newsRepository.findById(id).get();
        existingNews.setSubject(news.getSubject());
        existingNews.setDescription(news.getDescription());
        existingNews.setDate(news.getDate());
        return newsRepository.save(existingNews);
    }
}
