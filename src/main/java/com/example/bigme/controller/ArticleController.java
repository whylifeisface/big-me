package com.example.bigme.controller;

import com.example.bigme.pojo.Article;
import com.example.bigme.pojo.Category;
import com.example.bigme.pojo.PageBean;
import com.example.bigme.pojo.Result;
import com.example.bigme.service.ArticleService;
import com.example.bigme.utils.JwtUtil;
import com.example.bigme.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    @GetMapping("/list")
    public Result<Object> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        //分页查询文章列表
        PageBean<Article> list = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(list);
    }

    @PostMapping("/update")
    public Result<Object> update(
            @RequestBody Article article
    ){
        //确认存在
        Article art = articleService.findById(article.getId());

        //更新文章
        articleService.update(article);

        return Result.success();
    }


    @PostMapping("/add")
    public Result<Object> add(
            @RequestBody Article article
    ) {
        //添加文章
        articleService.add(article);
        return Result.success();
    }


}