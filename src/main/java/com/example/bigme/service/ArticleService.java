package com.example.bigme.service;

import com.example.bigme.pojo.Article;
import com.example.bigme.pojo.PageBean;
import org.springframework.stereotype.Service;

public interface ArticleService {

    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
