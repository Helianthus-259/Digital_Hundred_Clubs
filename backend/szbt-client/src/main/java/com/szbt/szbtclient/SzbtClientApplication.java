package com.szbt.szbtclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"org.example"})
public class SzbtClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzbtClientApplication.class, args);
    }

}
