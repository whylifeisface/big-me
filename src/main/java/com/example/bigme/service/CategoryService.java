package com.example.bigme.service;

import com.example.bigme.pojo.Category;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService {

    void add(Category category);

}
