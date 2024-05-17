package com.szbt.studentserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.example"})
public class StudentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServerApplication.class, args);
    }

}
