package com.example.bigme.controller;


import com.example.bigme.pojo.Result;
import com.example.bigme.pojo.User;
import com.example.bigme.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(
            @Pattern(regexp = "^\\S{5,16}$")
            @RequestParam String username,
            @Pattern(regexp = "^\\${5,16}$")
            @RequestParam String password
    ) {
            User user = userService.findUserByName(username);
            if (user == null) {
                userService.register(username, password);
            } else {
                return Result.error("用户名已被注册");
            }
            return Result.success();
        }

    }

