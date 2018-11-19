package com.zhu.thyme_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zhu.thyme_demo.mapper")
@ComponentScan(basePackages = { "com.zhu" })
public class ThymeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeDemoApplication.class, args);
    }
}
