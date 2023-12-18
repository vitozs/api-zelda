package com.github.zeldaservice.Repository;

import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import com.github.zeldaservice.model.favoriteModel.ReturnFavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteGameModel, Long> { // favoUrite, com U
// ALERT! você não precisa fazer query pra tudo!
    // dá uma olhada na doc do JPA. tem uma forma marota de fazer query só dando o nome corretamente pro métodp aqui

//    @Query(value = "select id from jogos where id_jogo = :id", nativeQuery=true)
//    Long findByid_jogo(@Param("id") String id);// camel case done wrong

    @Query(value = "SELECT users.name as username\n" +
            "FROM users\n" +
            "JOIN usuario_e_jogo_favorito ON usuario_e_jogo_favorito.id_usuario = users.id\n" +
            "WHERE users.id = :id LIMIT 1", nativeQuery=true)
    String findByname(@Param("id") Long id); // camel case done wrong


    @Query(value = "SELECT jogos.name as game, jogos.id_jogo as game_id, jogos.description as description\n" +
            "FROM users\n" +
            "JOIN usuario_e_jogo_favorito ON usuario_e_jogo_favorito.id_usuario = users.id\n" +
            "JOIN jogos ON usuario_e_jogo_favorito.id_jogo = jogos.id\n" +
            "WHERE users.id = :id", nativeQuery=true)
    List<ReturnFavoriteDTO> findByGames(@Param("id") Long id);
}
