package com.github.zeldaservice.Repository;

import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import com.github.zeldaservice.model.favoriteModel.ReturnFavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteGameModel, Long> {

    @Query(value = "select id from jogos where id_jogo = :id", nativeQuery=true)
    Long findByIdJogo(@Param("id") String id);

    @Query(value = "SELECT users.name as username\n" +
            "FROM users\n" +
            "JOIN usuario_e_jogo_favorito ON usuario_e_jogo_favorito.id_usuario = users.id\n" +
            "WHERE users.id = :id LIMIT 1", nativeQuery=true)
    String findByName(@Param("id") Long id);

    @Query(value = "SELECT jogos.name as game, jogos.id_jogo as game_id, jogos.description as description\n" +
            "FROM users\n" +
            "JOIN usuario_e_jogo_favorito ON usuario_e_jogo_favorito.id_usuario = users.id\n" +
            "JOIN jogos ON usuario_e_jogo_favorito.id_jogo = jogos.id\n" +
            "WHERE users.id = :id", nativeQuery=true)
    List<ReturnFavoriteDTO> findByGames(@Param("id") Long id);
}
