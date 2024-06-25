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
@ComponentScan(basePackages = {"org.example","com.szbt.filter","com.szbt.config"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String authServerUrl = uriConfiguration.getAuthServer();
        String studentServerUrl = uriConfiguration.getStudentServer();
        String fileServerUrl = uriConfiguration.getFileServer();
        String clubServerUrl = uriConfiguration.getClubServer();
        String adminServerUrl = uriConfiguration.getAdminServer();
        String activityServerUrl = uriConfiguration.getActivityServer();
        String springAdminUrl = uriConfiguration.getSpringAdmin();

        return builder.routes()
                .route("auth-route", r -> r.path("/api/auth/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(authServerUrl))

                .route("student-route", r -> r.path("/api/student/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(studentServerUrl))

                .route("file-route", r -> r.path("/api/file/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(fileServerUrl))

                .route("club-route", r -> r.path("/api/club/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(clubServerUrl))

                .route("admin-route", r -> r.path("/api/admin/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(adminServerUrl))

                .route("activity-route", r -> r.path("/api/activity/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(activityServerUrl))

//                .route("spring-admin", r -> r.path("/api/monitor/**")
//                        .filters(f -> f.rewritePath("/api/monitor/.*", ""))
//                        .uri(springAdminUrl))
                .build();
    }

    @PostConstruct
    public void init() {
        List<ServiceInstance> authInstances = discoveryClient.getInstances("AUTH-SERVER");
        System.out.println("auth-server instances: " + authInstances);

        List<ServiceInstance> studentInstances = discoveryClient.getInstances("STUDENT-SERVER");
        System.out.println("student-server instances: " + studentInstances);

        List<ServiceInstance> springAdminInstances = discoveryClient.getInstances("SPRING-ADMIN");
        System.out.println("spring-admin instances: " + springAdminInstances);
    }

}