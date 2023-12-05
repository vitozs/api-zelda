package com.github.reviewservice.reviewsDTO;

import lombok.Data;

import java.util.List;

@Data
public class ReturnGameModel {
    private GameModel gameModel;
    private List<ReviewResponseDTO> analises;

}
