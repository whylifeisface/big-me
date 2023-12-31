package com.example.bigme.interception;


import com.example.bigme.pojo.Result;
import com.example.bigme.utils.JwtUtil;
import com.example.bigme.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //获取token
        String token = request.getHeader("Authorization");

        try {

            //从redis中获取token
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            String redisToken = ops.get(token);
            if (redisToken == null) {
                throw new RuntimeException();
            }
            //解析token
            Map<String, Object> claims = JwtUtil.parseToken(token);

            //将token中的信息存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            //token无效，返回401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空数据
        ThreadLocalUtil.remove();
    }
}
