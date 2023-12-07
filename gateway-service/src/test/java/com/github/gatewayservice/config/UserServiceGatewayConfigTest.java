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
                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVc2VyVG9rZW4iLCJzdWIiOiJyb2JlcnQyQGdtYWlsLmNvbSIsImlkIjoxOSwiZXhwIjoxNzAxOTM4NDc1fQ.dd2oBwogtv7JQGHI_35kygyi9-x9U9m3ToD_I5NtQMQ")
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

    @Test
    public void update_deveRetornar200() {
        webTestClient.put().uri("/user/update")
                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVc2VyVG9rZW4iLCJzdWIiOiJyb2JlcnQyQGdtYWlsLmNvbSIsImlkIjoxOSwiZXhwIjoxNzAxOTM4NDc1fQ.dd2oBwogtv7JQGHI_35kygyi9-x9U9m3ToD_I5NtQMQ")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{}")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void create_deveRetornar200() {
        webTestClient.post().uri("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{ \n" +
                        "    \"name\": \"Robert Perquim\",\n" +
                        "    \"age\": 21,\n" +
                        "    \"email\" : \"robert22@gmail.com\",\n" +
                        "    \"password\": \"12345678\"\n" +
                        "}")
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void create_deveRetornar400() {
        webTestClient.post().uri("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{}")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void delete_deveRetornar200() {
        webTestClient.delete().uri("/user/delete")
                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVc2VyVG9rZW4iLCJzdWIiOiJyb2JlcnQyQGdtYWlsLmNvbSIsImlkIjoxOSwiZXhwIjoxNzAxOTM4NDc1fQ.dd2oBwogtv7JQGHI_35kygyi9-x9U9m3ToD_I5NtQMQ")
                .exchange()
                .expectStatus().isOk();
    }


    }
