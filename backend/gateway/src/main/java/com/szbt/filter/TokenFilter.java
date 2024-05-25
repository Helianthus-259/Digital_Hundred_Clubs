package com.szbt.filter;

import com.auth0.jwt.interfaces.Claim;
import org.example.constants.RequestKeyConstants;
import org.example.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class TokenFilter implements GlobalFilter, Ordered {

    private final JWTUtils jwtUtils;

    private final List<String> bypassPaths;

    private final String bypassPathPrefix;

    @Autowired
    public TokenFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        // 初始化绕过路径列表
        this.bypassPaths = Arrays.asList("/auth-server/auth/student/login", "/auth-server/auth/student/register");
        // 初始化绕过路径
        this.bypassPathPrefix = "/auth-server/auth";
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        System.out.println("path:"+path);

//        // 检查当前请求路径是否在绕过列表中
//        if (bypassPaths.contains(path)) {
//            return chain.filter(exchange);
//        }

        // 检查当前请求路径是否绕过
        if (path.startsWith(bypassPathPrefix)) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("token");

        // 检查token是否为空
        if (StringUtils.isEmpty(token)) {
            return unauthorized(exchange, "Token is empty");
        }

        // 解析JWT
        Map<String, Claim> claimMap = jwtUtils.parseJwt(token);
        if (claimMap == null) {
            return unauthorized(exchange, "Invalid token");
        }

        // 从claimMap中获取用户信息
        String id = claimMap.get(RequestKeyConstants.ID).asString();
        String name = claimMap.get(RequestKeyConstants.NAME).asString();
        String authority = claimMap.get(RequestKeyConstants.AUTHORITY).asString();
        String account = claimMap.get(RequestKeyConstants.Account).asString();

        // 将用户信息设置进header中，传递到下游服务
        Consumer<HttpHeaders> headers = httpHeaders -> {
            httpHeaders.add(RequestKeyConstants.ID, id);
            httpHeaders.add(RequestKeyConstants.NAME, name);
            httpHeaders.add(RequestKeyConstants.AUTHORITY, authority);
            httpHeaders.add(RequestKeyConstants.Account, account);
        };
        ServerHttpRequest modifiedRequest = request.mutate().headers(headers).build();

        // 权限校验（可以根据需要进行具体实现）

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, "text/plain");
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(message.getBytes())));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}