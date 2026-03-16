package com.me.springhomework002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hello", "com.me.springhomework002"})
public class SpringHomework002Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringHomework002Application.class, args);

    }

}
