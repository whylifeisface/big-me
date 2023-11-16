package com.example.bigme.service.imp;

import com.example.bigme.mapper.ArticleMapper;
import com.example.bigme.pojo.Category;
import com.example.bigme.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Category category) {


    }
}
