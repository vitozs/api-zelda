package com.github.zeldaservice.Repository;

import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteGameModel, Long> {

    @Query(value = "select id from games where id_game = :id", nativeQuery=true)
    Long findByid_game(@Param("id") String id);
}
