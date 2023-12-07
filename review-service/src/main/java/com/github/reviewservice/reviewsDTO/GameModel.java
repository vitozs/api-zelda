package com.github.reviewservice.reviewsDTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jogos")
@Data
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String developer;
    private String publisher;
    private String released_date;
    private String id_jogo;

}