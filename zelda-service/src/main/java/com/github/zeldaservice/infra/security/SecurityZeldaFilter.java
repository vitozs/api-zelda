package com.github.zeldaservice.infra.security;

import com.github.zeldaservice.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
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
    private final TokenService tokenService = new TokenService();
    private final String AuthURL = "http://localhost:8089/login/authentication";
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recoverToken(request);
        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
            bodyValues.add("email", subject);
            var responseEntity = WebClient.create(AuthURL)
                    .post()
                    .body(BodyInserters.fromFormData(bodyValues))
                    .retrieve()
                    .toEntity(Boolean.class)
                    .block();
            Boolean emailExists = responseEntity.getBody();
            if (responseEntity.getBody() != null ? responseEntity.getBody() : false) {
                var authentication = new UsernamePasswordAuthenticationToken(subject, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
