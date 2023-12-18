package com.github.zeldaservice.controller;

import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.model.ZeldaGameModel;
import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import com.github.zeldaservice.model.favoriteModel.ReturnFavoritesModel;
import com.github.zeldaservice.service.ZeldaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("zelda")
@Tag(name = "zelda-api")
public class ZeldaController {

    @Autowired // autowired nããão (tem outra classe que eu comentei o pq! :D)
    ZeldaService zeldaService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("all")
    public ResponseEntity<List<ZeldaGameModel>> getAll(){
        return new ResponseEntity<>(zeldaService.getAllGames().getData(), HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("game")
    public ResponseEntity<List<ZeldaGameModel>> getByName(@RequestParam String name) throws GameNotFoundException {
        return new ResponseEntity<>(zeldaService.getGameByName(name).getData(), HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("game/id/{id}")
    public ResponseEntity<ZeldaGameModel> getById(@PathVariable("id") String id) throws GameNotFoundException {
        return new ResponseEntity<>(zeldaService.getGameById(id).getSingleData(), HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("game/saveFavorite/{id}")
    public ResponseEntity<FavoriteGameModel> favoriteGame(@PathVariable("id") String id, HttpServletRequest request) {

        return new ResponseEntity<>( zeldaService.saveFavoriteGame(id, request),HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("game/Favorites")
    public ResponseEntity<ReturnFavoritesModel> getFavoriteGames(HttpServletRequest request) throws GameNotFoundException { // exception não lançada nem gerenciada (?)
// espaços!

        return new ResponseEntity<>(zeldaService.getFavoriteGames(request), HttpStatus.OK);
    }
}
