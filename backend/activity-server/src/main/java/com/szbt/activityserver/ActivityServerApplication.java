package com.szbt.activityserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.example"})
public class ActivityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityServerApplication.class, args);
    }

}
