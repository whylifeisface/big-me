package com.example.bigme.controller;


import com.example.bigme.pojo.Category;
import com.example.bigme.pojo.Result;
import com.example.bigme.service.ArticleService;
import com.example.bigme.service.CategoryService;
import com.example.bigme.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result<Object> add(
            @RequestBody @Validated(Category.Add.class) Category category
    ) {

        categoryService.add(category);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<Object> list() {

        List<Category> list =  categoryService.list();
        return Result.success(list);
    }

    @GetMapping("/detail")
    public Result<Object> detail(Integer id){
       Category category = categoryService.findById(id);
        return Result.success();
    }
    
    @PutMapping("/update")
    public Result<Object> update(
            @RequestBody @Validated(Category.Update.class) Category category
    ){
        categoryService.update(category);
        return Result.success();
    }

}
