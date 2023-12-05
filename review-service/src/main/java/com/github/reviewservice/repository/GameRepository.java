package com.github.reviewservice.repository;

import com.github.reviewservice.reviewsDTO.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {
    @Query(value = "select * from jogos where jogos.id_jogo = :id_jogo", nativeQuery=true)
    GameModel getGame(@Param("id_jogo") String id_jogo);

    @Query(value = "select jogos.id as id from jogos where jogos.id_jogo = :id_jogo", nativeQuery=true)
    Long getGameIdByCode(@Param("id_jogo") String id_jogo);
}
