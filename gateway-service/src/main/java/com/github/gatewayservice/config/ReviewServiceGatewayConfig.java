package com.github.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewServiceGatewayConfig {
    @Bean
    public RouteLocator customRouteLocatorRevieq(RouteLocatorBuilder builder){
        return builder.routes()
                .route("update", r -> r.path("/review/update")
                        .uri("http://localhost:8088/"))
                .route("create", r -> r.path("/review/create")
                        .uri("http://localhost:8088/"))
                .route("get-by-game", r -> r.path("/review/get-by-game")
                        .uri("http://localhost:8088/"))
                .route("get-by-user", r -> r.path("/review/get-by-user")
                        .uri("http://localhost:8088/"))
                .build();
    }
}
