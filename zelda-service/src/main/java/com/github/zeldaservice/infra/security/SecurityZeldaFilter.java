package com.github.zeldaservice.infra.security;

import com.github.zeldaservice.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;


public class SecurityZeldaFilter extends OncePerRequestFilter {
    private TokenService tokenService = new TokenService();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT =  recoverToken(request);
        if(tokenJWT != null){

            String subject = tokenService.getSubject(tokenJWT);
            MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
            bodyValues.add("email", subject);

            ResponseEntity<Boolean> responseEntity = WebClient.create("http://localhost:8089/login/authentication")
                    .post()
                    .body(BodyInserters.fromFormData(bodyValues))
                    .retrieve()
                    .toEntity(Boolean.class).block();

            Boolean emailExists = responseEntity.getBody();

            if (emailExists != null && emailExists) {
                var authentication = new UsernamePasswordAuthenticationToken(subject, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;

    }
}
