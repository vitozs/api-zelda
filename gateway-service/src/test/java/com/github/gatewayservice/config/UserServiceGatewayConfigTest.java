package com.github.gatewayservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest
@AutoConfigureWebTestClient
public class UserServiceGatewayConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void login_DeveRetornar200() {
        webTestClient.post().uri("/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{ \n" +
                        "    \"email\" : \"robert@gmail.com\",\n" +
                        "    \"password\": \"12345678\"\n" +
                        "}")
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void login_DeveRetornar401() {
        webTestClient.post().uri("/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{}")
                .exchange()
                .expectStatus().isUnauthorized();

    }

    @Test
    public void profile_deveRetornar200() {
        webTestClient.get().uri("/user/profile")
                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVc2VyVG9rZW4iLCJzdWIiOiJyb2JlcnRAZ21haWwuY29tIiwiaWQiOjE1LCJleHAiOjE3MDE5MzQ3OTF9.4zw28Yr-ZcIdptp82Vrm-9daZD7O1ml0d98Pl789Ah4")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void profile_deveRetornar403() {
        webTestClient.get().uri("/user/profile")
                .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                .exchange()
                .expectStatus().isForbidden();
    }


    }
