package com.github.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZeldaServiceGatewayConfig {
    @Bean
    public RouteLocator customRouteLocatorZelda(RouteLocatorBuilder builder){
        return builder.routes()
                .route("zelda-service-all", r -> r.path("/zelda/all")
                        .uri("http://localhost:8090/"))
                .route("zelda-service-game", r -> r.path("/zelda/game")
                        .uri("http://localhost:8090/"))
                .route("zelda-service-game-id", r -> r.path("/zelda/game/id/**")
                        .filters(f -> f.rewritePath("/(?<id>.*)", "/${id}"))
                        .uri("http://localhost:8090/"))
                .route("zelda-service-Favorites", r -> r.path("/zelda/game/Favorites")
                        .uri("http://localhost:8090/"))
                .route("zelda-service-game-id", r -> r.path("/zelda/game/saveFavorite/**")
                        .filters(f -> f.rewritePath("/(?<id>.*)", "/${id}"))
                        .uri("http://localhost:8090/"))
                .build();
    }
}
