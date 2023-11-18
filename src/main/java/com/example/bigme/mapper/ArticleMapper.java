package com.example.bigme.mapper;


import com.example.bigme.pojo.Article;
import com.example.bigme.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(titlem, content,cover_img,state,category_id,create_user,create_time,update_time" +
            "values(#{title}, #{content} , #{coverImg} ,#{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime}))")
    void add(Article article);

    List<Article> list(Integer categoryId, String state, Integer id);
}
