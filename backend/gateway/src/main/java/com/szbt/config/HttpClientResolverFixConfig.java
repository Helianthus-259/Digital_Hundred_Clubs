package com.szbt.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;


@Configuration
public class HttpClientResolverFixConfig {
    @Bean
    public HttpClientCustomizer httpClientResolverCustomizer() {
        return new HttpClientCustomizer() {
            @Override
            public reactor.netty.http.client.HttpClient customize(reactor.netty.http.client.HttpClient httpClient) {
                return httpClient.resolver(DefaultAddressResolverGroup.INSTANCE);
            }
        };
    }
}


