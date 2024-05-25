package com.szbt;

import com.szbt.config.UriConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableConfigurationProperties(UriConfiguration.class)
@ComponentScan(basePackages = {"org.example","com.szbt.filter"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String authServerUri = uriConfiguration.getAuthServer();
        String studentServerUri = uriConfiguration.getStudentServer();

        return builder.routes()
                .route("auth-route", r -> r.path("/auth-server/**")
                        .filters(f -> f.rewritePath("/auth-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://127.0.0.1:8084"))
                .route("student-route", r -> r.path("/student-server/**")
                        .filters(f -> f.rewritePath("/student-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://127.0.0.1:8081"))
                .build();
    }

    @PostConstruct
    public void init() {
        List<ServiceInstance> authInstances = discoveryClient.getInstances("AUTH-SERVER");
        System.out.println("auth-server instances: " + authInstances);

        List<ServiceInstance> studentInstances = discoveryClient.getInstances("STUDENT-SERVER");
        System.out.println("student-server instances: " + studentInstances);
    }

}