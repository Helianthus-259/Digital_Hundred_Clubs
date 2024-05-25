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
        String authServerUri = uriConfiguration.getAuthServer();
        String studentServerUri = uriConfiguration.getStudentServer();
        String fileServerUri = uriConfiguration.getFileServer();
        String clubServerUri = uriConfiguration.getClubServer();
        String adminServerUri = uriConfiguration.getAdminServer();
        String activityServerUri = uriConfiguration.getActivityServer();

        return builder.routes()
                .route("auth-route", r -> r.path("/api/auth/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(authServerUri))

                .route("student-route", r -> r.path("/api/student/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(studentServerUri))

                .route("file-route", r -> r.path("/api/file/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(fileServerUri))

                .route("club-route", r -> r.path("/api/club/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(clubServerUri))

                .route("admin-route", r -> r.path("/api/admin/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(adminServerUri))

                .route("activity-route", r -> r.path("/api/activity/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/${segment}"))
                        .uri(activityServerUri))
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