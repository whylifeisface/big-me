package com.example.bigme.service.imp;

import com.example.bigme.mapper.ArticleMapper;
import com.example.bigme.pojo.Article;
import com.example.bigme.pojo.Category;
import com.example.bigme.service.ArticleService;
import com.example.bigme.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;



    @Override
    public void add(Article article) {

        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        article.setCreateUser(id);
        articleMapper.add(article);
    }
}
