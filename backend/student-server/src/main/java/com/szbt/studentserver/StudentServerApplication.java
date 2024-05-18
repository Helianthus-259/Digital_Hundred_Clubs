package com.szbt.studentserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.example"})
public class StudentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServerApplication.class, args);
    }

}
