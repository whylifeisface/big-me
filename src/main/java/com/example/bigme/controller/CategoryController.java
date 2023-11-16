package com.example.bigme.controller;


import com.example.bigme.pojo.Category;
import com.example.bigme.pojo.Result;
import com.example.bigme.service.ArticleService;
import com.example.bigme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result<Object> add(
            @RequestBody Category category
    ) {


        categoryService.add(category);
        return Result.success();
    }
}
