package com.github.reviewservice.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.reviewservice.repository.GameRepository;
import com.github.reviewservice.repository.ReviewRepository;
import com.github.reviewservice.reviewsDTO.ReviewModel;
import com.github.reviewservice.reviewsDTO.ReturnGameModel;
import com.github.reviewservice.reviewsDTO.ReviewRequestDTO;
import com.github.reviewservice.reviewsDTO.ReviewResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    @Autowired
    GameRepository gameRepository;
    @Value("${api.security.token.secret}")
    private String secret;

    private String decodeJWT(HttpHeaders headers){
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION); //pega o token
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


    public ReviewModel createReview(HttpHeaders headers, ReviewRequestDTO analiseUser) {
        String email = decodeJWT(headers);
        ReviewModel analise = new ReviewModel();

        analise.setId_usuario(reviewRepository.getIdByEmail(email));
        analise.setId_jogo(gameRepository.getGameIdByCode(analiseUser.getId_jogo()));
        analise.setAnalise(analiseUser.getAnalise());

        return reviewRepository.save(analise);


    }

    public ReviewModel updateReview(HttpHeaders headers, String id, ReviewRequestDTO analiseUser){
        String email = decodeJWT(headers);
        ReviewModel analise = reviewRepository.getReferenceById(Long.valueOf(id));
        analise.setAnalise(analiseUser.getAnalise());
        return reviewRepository.saveAndFlush(analise);

    }

    public ReturnGameModel getAllByGame(String id_jogo) {
        ReturnGameModel gameAndReviewModel = new ReturnGameModel();

        gameAndReviewModel.setGameModel(gameRepository.getGame(id_jogo));
        gameAndReviewModel.setAnalises(reviewRepository.getAnalises(id_jogo));

        return gameAndReviewModel;

    }
}
