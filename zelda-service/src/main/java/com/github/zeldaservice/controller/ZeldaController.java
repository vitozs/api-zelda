package com.github.zeldaservice.controller;

import com.github.zeldaservice.exception.GameNotFoundException;
import com.github.zeldaservice.model.ZeldaGameModel;
import com.github.zeldaservice.service.ZeldaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("zelda")
public class ZeldaController {

    @Autowired
    ZeldaService zeldaService;

    @GetMapping("all")
    public ResponseEntity<List<ZeldaGameModel>> getAll(){
        return new ResponseEntity<>(zeldaService.getAllGames().getData(), HttpStatus.OK);
    }

    @GetMapping("game")
    public ResponseEntity<List<ZeldaGameModel>> getByName(@RequestParam String name) throws GameNotFoundException {
        return new ResponseEntity<>(zeldaService.getGameByName(name).getData(), HttpStatus.OK);
    }

    @GetMapping("game/id/{id}")
    public ResponseEntity<ZeldaGameModel> getById(@PathVariable("id") String id) throws GameNotFoundException {
        return new ResponseEntity<>(zeldaService.getGameById(id).getSingleData(), HttpStatus.OK);
    }
}