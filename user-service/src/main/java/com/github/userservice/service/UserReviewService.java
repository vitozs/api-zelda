package com.github.userservice.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.userservice.models.reviewsDTO.ReviewModel;
import com.github.userservice.models.reviewsDTO.ReviewRequestDTO;
import com.github.userservice.models.reviewsDTO.ReviewResponseDTO;
import com.github.userservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Value("${api.security.token.secret}")
    private String secret;

    private String decodeJWT(HttpHeaders headers){
        String token = headers.getFirst(org.springframework.http.HttpHeaders.AUTHORIZATION); //pega o token
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("UserToken")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token.replaceAll("Bearer ", "")); // formata e valida o token

        String email =  decodedJWT.getSubject();
        return email;
    }

    public List<ReviewResponseDTO> getAllReviews(HttpHeaders headers){
        String email = decodeJWT(headers);
        return reviewRepository.getAllReviewsByUser(email);
    }


    public Object createReview(HttpHeaders headers, ReviewRequestDTO analiseUser) {
        String email = decodeJWT(headers);
        ReviewModel analise = new ReviewModel();
        analise.setId_usuario(reviewRepository.getIdByEmail(email));
        analise.setId_jogo(reviewRepository.getGameIdByCode(analiseUser.getId_jogo()));
        analise.setAnalise(analiseUser.getAnalise());

        return reviewRepository.save(analise);


    }

    public ReviewModel updateReview(HttpHeaders headers, String id, ReviewRequestDTO analiseUser){
        String email = decodeJWT(headers);
        ReviewModel analise = reviewRepository.getReferenceById(Long.valueOf(id));
        analise.setAnalise(analiseUser.getAnalise());
        return reviewRepository.saveAndFlush(analise);

    }
}
