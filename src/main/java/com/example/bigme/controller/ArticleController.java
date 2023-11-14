package com.example.bigme.controller;

import com.example.bigme.pojo.Result;
import com.example.bigme.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @GetMapping("/list")
    public Result<Object> list() {

    return Result.success();
    }
}