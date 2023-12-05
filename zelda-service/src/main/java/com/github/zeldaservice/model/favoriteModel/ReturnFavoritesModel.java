package com.github.zeldaservice.model.favoriteModel;


import lombok.Data;

import java.util.List;

@Data
public class ReturnFavoritesModel {

    private String userModel;
    private List<ReturnFavoriteDTO> favoriteGamesList;
}
