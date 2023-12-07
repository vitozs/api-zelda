package com.github.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceGatewayConfig {

    @Bean
    public RouteLocator customRouteLocatorUser(RouteLocatorBuilder builder){
        return builder.routes()
                .route("login", r -> r.path("/login/user")
                        .uri("http://localhost:8085/"))
                .route("user-service-update", r -> r.path("/user/update")
                        .uri("http://localhost:8085/"))
                .route("user-service-profile", r -> r.path("/user/profile")
                    .uri("http://localhost:8085/"))
                .route("user-service-create", r -> r.path("/user/create")
                        .uri("http://localhost:8085/"))
                .route("user-service-authentication", r -> r.path("/login/authentication")
                        .uri("http://localhost:8085/"))
                .route("user-service-delete", r -> r.path("/user/delete")
                        .uri("http://localhost:8085/"))
                .build();
    }



}
