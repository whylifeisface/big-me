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

    // 添加分类
    @PostMapping("/add")
    public Result<Object> add(
            @RequestBody @Validated(Category.Add.class) Category category
    ) {

        // 调用服务层添加分类
        categoryService.add(category);
        return Result.success();
    }

    // 获取分类列表
    @GetMapping("/list")
    public Result<Object> list() {

        // 调用服务层获取分类列表
        List<Category> list =  categoryService.list();
        return Result.success(list);
    }

    // 获取分类详情
    @GetMapping("/detail")
    public Result<Object> detail(Integer id){
        // 调用服务层根据id获取分类详情
        Category category = categoryService.findById(id);
        return Result.success();
    }

    // 更新分类
    @PutMapping("/update")
    public Result<Object> update(
            @RequestBody @Validated(Category.Update.class) Category category
    ){
        // 调用服务层更新分类
        categoryService.update(category);
        return Result.success();
    }

}
