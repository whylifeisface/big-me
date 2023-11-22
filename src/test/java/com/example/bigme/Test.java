package com.example.bigme;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

@SpringBootTest
public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        String pass = "twxpass";
        String s = DigestUtils.md5DigestAsHex(pass.getBytes());
        System.out.println(s);
        DigestUtils.md5Digest(pass.getBytes()).equals("��1\f\u0012�4\u000E]���E���");
    }
}
