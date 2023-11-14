package com.example.bigme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class ThreadLocalTest {

    @Test
    public void test(){
        ThreadLocal<String> local = new ThreadLocal<>();
        new Thread(() -> {
            local.set("蓝色");
            System.out.println(Thread.currentThread().getName() + "" + local.get());
        },"蓝蓝").start();

        new Thread(() -> {
            local.set("绿色");
            System.out.println(Thread.currentThread().getName() + "" + local.get());
        },"绿绿").start();
    }

}
