package com.szbt.clubserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"org.example"})
@ComponentScan(basePackages = {"org.example","com.szbt.clubserver"})
public class ClubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubServerApplication.class, args);
    }
}
