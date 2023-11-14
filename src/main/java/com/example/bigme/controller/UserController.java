package com.example.bigme.controller;


import com.auth0.jwt.JWT;
import com.example.bigme.pojo.Result;
import com.example.bigme.pojo.User;
import com.example.bigme.service.UserService;
import com.example.bigme.utils.JwtUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Object> register(
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

    @PostMapping("/login")
    public Result<Object> login(
            String username,
            String password
    ) {
        User user = userService.findUserByName(username);
        if (user == null) {
            return Result.error("用户名错误");
        }
        if (Arrays.toString(DigestUtils.md5Digest(password.getBytes())).equals(user.getPassword())) {
            HashMap<String, Object> claim = new HashMap<>();
            claim.put("id", user.getId());
            claim.put("username", user.getUsername());
            String token = JwtUtil.genToken(claim);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }


    @GetMapping("/userInfo")
    public Result<User> userInfo(
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> claim = JwtUtil.parseToken(token);
        String username = (String) claim.get("username");
        User user = userService.findUserByName(username);
        return Result.success(user);
    }
}

