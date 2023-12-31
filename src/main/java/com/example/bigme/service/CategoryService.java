package com.example.bigme.service;

import com.example.bigme.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
