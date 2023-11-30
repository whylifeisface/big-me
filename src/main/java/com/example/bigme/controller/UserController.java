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
        // 用户名，长度5-16位，必须包含数字或字母
        @Pattern(regexp = "^\\S{5,16}$")
        @RequestParam String username,
        // 密码，长度16位，必须包含数字或字母
        //(?=.*[0-9]) 表示必须至少包含一个数字。(?=.*[a-zA-Z]) 表示必须至少包含一个字母。(?=.*[@#$%^&+=]) 表示必须至少包含一个特殊字符（@、#、$、%、^、&、+、=）。
        //(?=\\S+$) 表示密码的结尾必须是一个非空字符。
        //(?=\\S+$).{16,} 表示密码的长度必须在16位以上
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{0,16}")
        @RequestParam String password
) {

    // 判断用户名是否已被注册
    User user = userService.findUserByName(username);
    if (user == null) {
        // 注册
        userService.register(username, password);
    } else {
        // 返回错误信息
        return Result.error("用户名已被注册");
    }
    // 返回成功信息
    return Result.success();
}

    @PostMapping("/login")
    public Result<Object> login(
            // 用户名
            String username,
            // 密码
            String password
    ) {
        // 判断用户名是否已被注册
        User user = userService.findUserByName(username);
        if (user == null) {
            // 返回错误信息
            return Result.error("用户名错误");
        }
        // 判断密码是否正确
        if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            // 生成token
            HashMap<String, Object> claim = new HashMap<>();
            claim.put("id", user.getId());
            claim.put("username", user.getUsername());
            String token = JwtUtil.genToken(claim);
            // 将token存入redis
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set(token, token, 1, TimeUnit.HOURS);
            // 返回token
            return Result.success(token);
        }

        // 返回错误信息
        return Result.error("密码错误");
    }


    @GetMapping("/userInfo")
    public Result<User> userInfo(
            // 获取token
            @RequestHeader("Authorization") String token
    ) {
        // 解析token
        Map<String, Object> claim = JwtUtil.parseToken(token);
        // 获取用户名
        String username = (String) claim.get("username");
        // 根据用户名查询用户信息
        User user = userService.findUserByName(username);
        // 返回用户信息
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result<Object> update(
            // 获取用户信息
            @RequestBody @Validated User user
    ) {
        // 更新用户信息
        userService.update(user);
        // 返回成功信息
        return Result.success();
    }

    @PostMapping("/updateAvatar")
    public Result updateAvatar(
            // 获取头像
            @RequestParam String avatar
    ) {
        // 更新头像
        userService.updateAvatar(avatar);
        // 返回成功信息
        return Result.success();
    }

    @PostMapping("/updatePwd")
    public Result<Object> updatePwd(
            // 获取参数
            @RequestBody Map<String, String> params,
            // 获取token
            @RequestParam("Authorization") String token
    ) {

        // 获取参数
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        // 校验参数
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)) {
            // 返回错误信息
            return Result.error("缺少必要的参数");
        }


        //原密码是否正确
        // 获取用户名
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        // 根据用户名查询用户信息
        User userByName = userService.findUserByName(username);
        // 获取密码
        String password = userByName.getPassword();
        // 判断原密码是否正确
        if (!password.equals(Arrays.toString(DigestUtils.md5Digest(old_pwd.getBytes())))) {
            // 返回错误信息
            return Result.error("原密码不正确");
        }

        // new_pwd 和 re_pwd
        // 判断两次密码是否一致
        if (!re_pwd.equals(new_pwd)) {
            // 返回错误信息
            return Result.error("两次密码不一致");
        }

        // 更新密码
        userService.updatePwd(new_pwd);

        // 删除token
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.getOperations().delete(token);

        // 返回成功信息
        return Result.success();
    }

}

