package com.github.reviewservice.repository;

import com.github.reviewservice.reviewsDTO.ReviewModel;
import com.github.reviewservice.reviewsDTO.ReviewResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {

    @Query(value = "select analises.id as id, users.name as username, jogos.name as game, analises.analise from users join analises on analises.id_usuario = users.id join jogos on analises.id_jogo = jogos.id where users.email = :email", nativeQuery=true)
    List<ReviewResponseDTO> getAllReviewsByUser(@Param("email") String email);
    @Query(value = "select users.name as username, jogos.name as game, analises.analise from users join analises on analises.id_usuario = users.id join jogos on analises.id_jogo = jogos.id where jogos.id_jogo = :id_jogo", nativeQuery=true)
    List<ReviewResponseDTO> getAnalises(@Param("id_jogo") String id_jogo);
    @Query(value = "select users.id as id from users where users.email = :email", nativeQuery=true)
    Long getIdByEmail(@Param("email") String email);




}
