package com.example.bigme.service.imp;

import com.example.bigme.mapper.CategoryMapper;
import com.example.bigme.pojo.Category;
import com.example.bigme.service.CategoryService;
import com.example.bigme.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.list(id);
    }

    @Override
    public Category findById(Integer id) {

        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {

        categoryMapper.update(category);
    }
}
