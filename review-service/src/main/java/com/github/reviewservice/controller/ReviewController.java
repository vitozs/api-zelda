package com.github.reviewservice.controller;

import com.github.reviewservice.reviewsDTO.ReturnGameModel;
import com.github.reviewservice.reviewsDTO.ReviewRequestDTO;
import com.github.reviewservice.reviewsDTO.ReviewResponseDTO;
import com.github.reviewservice.service.UserReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@Tag(name = "review-api")
public class ReviewController {

    @Autowired
    UserReviewService userReviewService;

    @SecurityRequirement(name = "Bearer Authentication")

    @GetMapping("get-by-user")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(userReviewService.getAllReviews(headers), HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")

    @GetMapping("get-by-game")
    public ResponseEntity<ReturnGameModel> getAllByGame(@RequestParam String id_jogo){
        return new ResponseEntity<>(userReviewService.getAllByGame(id_jogo), HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")

    @PostMapping("create")
    public ResponseEntity<?> createReview(@RequestHeader HttpHeaders headers, @RequestBody ReviewRequestDTO analiseUser){
        return new ResponseEntity<>(userReviewService.createReview(headers, analiseUser), HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")

    @PostMapping("update")
    public ResponseEntity<?> updateReview(@RequestHeader HttpHeaders headers, @RequestParam String id, @RequestBody ReviewRequestDTO analiseUser){
        return new ResponseEntity<>(userReviewService.updateReview(headers, id, analiseUser), HttpStatus.OK);
    }

}
