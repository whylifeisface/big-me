package com.example.bigme.service.imp;

import com.example.bigme.mapper.CategoryMapper;
import com.example.bigme.pojo.Category;
import com.example.bigme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper
    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }
}
