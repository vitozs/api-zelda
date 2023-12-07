package com.github.gatewayservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

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


    }
