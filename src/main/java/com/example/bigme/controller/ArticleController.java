package com.example.bigme.controller;

import com.example.bigme.pojo.Category;
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


    @GetMapping("/list")
    public Result<Object> list() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");

        return Result.success();
    }



}