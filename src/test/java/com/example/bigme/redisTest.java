package com.example.bigme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class redisTest {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void setKey(){
        ValueOperations<String, String> opts = redisTemplate.opsForValue();
        opts.set("username", "oi");
    }

    @Test
    public void getKey(){
        ValueOperations<String, String> opts = redisTemplate.opsForValue();
        opts.get("username");
    }
}
