package com.szbt.activityserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.example"})
@ComponentScan(basePackages = {"org.example","com.szbt.activityserver"})
public class ActivityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityServerApplication.class, args);
    }

}
