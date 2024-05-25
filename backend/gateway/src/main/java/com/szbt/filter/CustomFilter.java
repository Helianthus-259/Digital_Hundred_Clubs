package com.szbt.filter;

import org.example.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

//@Component
//@Order(LOWEST_PRECEDENCE)
//public class CustomFilter implements GlobalFilter, Ordered {
//    private final List<String> bypassPaths;
//
//    @Autowired
//    public CustomFilter() {
//        // 初始化绕过路径列表
//        this.bypassPaths = Arrays.asList("/auth-server/auth/student/login", "/auth-server/auth/student/register");
//    }
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        String path = request.getURI().toString();
//        System.out.println("path: " + path);
//
//        // 检查当前请求路径是否在绕过列表中
//        if (bypassPaths.contains(path)) {
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                // 获取转发后的路径
//                String forwardedPath = String.valueOf(exchange.getRequest().getURI());
//
//                // 在这里可以对转发后的路径进行处理或记录
//                System.out.println("Original Path: " + path);
//                System.out.println("Forwarded Path: " + forwardedPath);
//            }));
//        }
//
//        // 继续执行过滤器链
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return LOWEST_PRECEDENCE;
//    }
//}