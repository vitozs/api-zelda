package com.github.zeldaservice.model.favoriteModel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario_e_jogo_favorito")
@Data
public class FavoriteGameModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_usuario;
    private Long id_jogo;
}
