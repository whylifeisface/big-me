package com.example.bigme.mapper;


import com.example.bigme.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values(#)")
    void add(Category category);

}
