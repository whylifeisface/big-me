package com.example.bigme.controller;


import com.auth0.jwt.JWT;
import com.example.bigme.pojo.Result;
import com.example.bigme.pojo.User;
import com.example.bigme.service.UserService;
import com.example.bigme.utils.JwtUtil;
import com.example.bigme.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    406
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
        if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            HashMap<String, Object> claim = new HashMap<>();
            claim.put("id", user.getId());
            claim.put("username", user.getUsername());
            String token = JwtUtil.genToken(claim);
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set(token, token, 1, TimeUnit.HOURS);
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

    @PostMapping("/update")
    public Result<Object> update(
            @RequestBody @Validated User user
    ) {
        userService.update(user);
        return Result.success();
    }

    @PostMapping("/updateAvatar")
    public Result updateAvatar(
            @RequestParam String avatar
    ) {
        userService.updateAvatar(avatar);
        return Result.success();
    }

    @PostMapping("/updatePwd")
    public Result<Object> updatePwd(
            @RequestBody Map<String, String> params,
            @RequestParam("Authorization") String token
    ) {

        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        // 校验参数
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)) {
            return Result.error("缺少必要的参数");
        }


        //原密码是否正确
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User userByName = userService.findUserByName(username);
        String password = userByName.getPassword();
        if (!password.equals(Arrays.toString(DigestUtils.md5Digest(old_pwd.getBytes())))) {
            return Result.error("原密码不正确");
        }

        // new_pwd 和 re_pwd
        if (!re_pwd.equals(new_pwd)) {
            return Result.error("两次密码不一致");
        }

        userService.updatePwd(new_pwd);

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.getOperations().delete(token);

        return Result.success();
    }
}

