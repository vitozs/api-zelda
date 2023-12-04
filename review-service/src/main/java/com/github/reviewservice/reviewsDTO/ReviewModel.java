package com.github.reviewservice.reviewsDTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "analises")
@Data
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_usuario;
    private Long id_jogo;
    private String analise;
}
