package com.github.zeldaservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

   // @Value("${api.security.token.secret}")
    private String secret = "12345678"; //NAO ESTA PEGANDO NA PROPETIS // nunca coloque secres no source code.
    public String getSubject(String tokenJWT){
//        try {
//            /*var!*/Algorithm algorithm = Algorithm.HMAC256(secret);
//            return JWT.require(algorithm)
//                    .withIssuer("UserToken") // coloca em constants isso
//                    .build()
//                    .verify(tokenJWT)
//                    .getSubject();
//        } catch (JWTVerificationException exception) {
//            throw new RuntimeException("Invalid or expired JWT token!");
//            // ->>>>>> vc lançou a RuntimeException mas não captura ela no controllerAdvice explicitamente pra controlar o output
//            // evite lançar exceptions genéricas. é melhor deixar esse método quebrar e capturar no adivce.
//        }
//
        // ...tipo assim:
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("UserToken") // coloca em constants isso
                .build()
                .verify(tokenJWT)
                .getSubject();
    }

    public Long getIdUser(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("UserToken")
                    .build()
                    .verify(tokenJWT)
                    .getClaim("id").asLong();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT token!");
        }
    }
}
