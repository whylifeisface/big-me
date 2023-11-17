package com.example.bigme.service;

import com.example.bigme.pojo.Article;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

    void add(Article article);

}
