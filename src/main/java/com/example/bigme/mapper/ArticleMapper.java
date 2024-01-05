package com.example.bigme.mapper;


import com.example.bigme.pojo.Article;
import com.example.bigme.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title, content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values(#{title}, #{content} , #{coverImg} ,#{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    //在xml里面实现了
//    @Select("<script>select * from article where create_user = #{id} <when test='categoryId != null'> and categoryId = #{categoryId}</when> " +
//            "<when test='state != null'> and state = #{state}</when> </script>")
    List<Article> list(Integer categoryId, String state, Integer id);

    @Select("select title, content,cover_img,state,category_id,create_user,create_time,update_time from article where id = #{id}")
    Article findById(Integer id);

    @Update("update article set " +
            "title = #{title},content = #{content}, state = #{state},category_id = #{categoryId}  " +
            "create_user = #{createUser}, create_time = #{createTime}, update_time = #{updateTime}" +
            "where id = #{id}")
    void update(Article article);

}
