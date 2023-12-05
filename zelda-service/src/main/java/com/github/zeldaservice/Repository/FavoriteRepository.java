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

    @Query(value = "select id from games where id_game = :id", nativeQuery=true)
    Long findByid_game(@Param("id") String id);

    @Query(value = "SELECT users.name as username\n" +
            "FROM users\n" +
            "JOIN user_and_favorite_game ON user_and_favorite_game.id_user = users.id\n" +
            "WHERE users.id = :id LIMIT 1", nativeQuery=true)
    String findByname(@Param("id") Long id);

    @Query(value = "SELECT games.name as game, games.id_game as game_id, games.description as description\n" +
            "FROM users\n" +
            "JOIN user_and_favorite_game ON user_and_favorite_game.id_user = users.id\n" +
            "JOIN games ON user_and_favorite_game.id_game = games.id\n" +
            "WHERE users.id = :id", nativeQuery=true)
    List<ReturnFavoriteDTO> findByGames(@Param("id") Long id);
}
