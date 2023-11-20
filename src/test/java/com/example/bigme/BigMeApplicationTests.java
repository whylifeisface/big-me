package com.example.bigme;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BigMeApplicationTests {

    @Test
    void contextLoads() {

//        HashMap<String, Object> claim = new HashMap<>();
//        claim.put("id", 1);
//        claim.put("username", "遗憾");
//        String sign = JWT.create()
//                .withClaim("user", claim)// 负载
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//  过期时间 1000天
//                .sign(Algorithm.HMAC256("faker"));//配置密钥
//        System.out.println(sign);


    }

    @Test
    public void getClaim() {
//        JWTVerifier faker = JWT.require(Algorithm.HMAC256("faker")).build();
//        DecodedJWT verify = faker.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IumBl-aGviJ9LCJleHAiOjE2OTk5OTM3OTJ9.Wew8iXyO7pJLfsfIeZAw8hBQLsB6hzy_AQPt1Yz2RoE\n");
//
//        Map<String, Claim> claims = verify.getClaims();
//        Claim user = claims.get("user");
//        Claim id = claims.get("id");
//        String u = user.asString();
//        Integer i = id.asInt();
//        System.out.println(u);
//        System.out.println(i);
    }
}
