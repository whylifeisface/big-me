package com.example.bigme.service.imp;

import com.example.bigme.mapper.ArticleMapper;
import com.example.bigme.pojo.Article;
import com.example.bigme.pojo.Category;
import com.example.bigme.pojo.PageBean;
import com.example.bigme.service.ArticleService;
import com.example.bigme.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Service

public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public void add(Article article) {

        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建bean
        PageBean<Article> bean = new PageBean<>();

        //开启分页
        PageHelper.offsetPage(pageNum, pageSize);
        // 调用方法

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Article> articles = articleMapper.list(categoryId, state, id);

        Page<Article> articlePageInfo = (Page<Article>) articles;
        bean.setTotal(articlePageInfo.getTotal());
        bean.setItems(articlePageInfo.getResult());
        return bean;
    }

    @Override
    public Article findById(Integer id) {
         return articleMapper.findById(id);

    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }
}
