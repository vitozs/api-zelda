package com.github.zeldaservice.model.favoriteModel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_and_favorite_game")
@Data
public class FavoriteGameModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_user;
    private Long id_game;
}
