package com.github.zeldaservice.controller;

import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.model.RequestModel;
import com.github.zeldaservice.model.SingleRequestModel;
import com.github.zeldaservice.model.ZeldaGameModel;
import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import com.github.zeldaservice.service.ZeldaService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ZeldaControllerTest {

    @Mock
    private ZeldaService zeldaService;

    @InjectMocks
    private ZeldaController zeldaController;

    @InjectMocks
    private RequestModel requestModel;

    @InjectMocks
    private SingleRequestModel singleRequestModel;

    @InjectMocks
    private FavoriteGameModel favoriteGameModel;

    @Test
    void testGetAll() {

        Mockito.when(zeldaService.getAllGames()).thenReturn(requestModel);

        ResponseEntity<List<ZeldaGameModel>> response = zeldaController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetById() throws GameNotFoundException {
        String gameId = "5f6ce9d805615a85623ec2ca";
        Mockito.when(zeldaService.getGameById(gameId)).thenReturn(singleRequestModel);

        ResponseEntity<ZeldaGameModel> response = zeldaController.getById(gameId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void testGetById_GameNotFoundException() throws GameNotFoundException {
        String nonExistentGameId = "9daksjd";
        Mockito.when(zeldaService.getGameById(nonExistentGameId)).thenThrow(new GameNotFoundException("Game not found"));

        assertThrows(GameNotFoundException.class, () -> zeldaController.getById(nonExistentGameId));
    }

    @Test
    void testGetByName() throws GameNotFoundException {
        String gameName = "Zelda";
        Mockito.when(zeldaService.getGameByName(gameName)).thenReturn(requestModel);

        ResponseEntity<List<ZeldaGameModel>> response = zeldaController.getByName(gameName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetByName_GameNotFoundException() throws GameNotFoundException {
        String nonExistentGameName = "jdkskl";
        Mockito.when(zeldaService.getGameByName(nonExistentGameName)).thenThrow(new GameNotFoundException("Game not found"));

        assertThrows(GameNotFoundException.class, () -> zeldaController.getByName(nonExistentGameName));
    }


    @Test
    void testSaveFavoriteGame() {
        String gameId = "5f6ce9d805615a85623ec2ca";
        HttpServletRequest request = mock(HttpServletRequest.class);

        Mockito.when(zeldaService.saveFavoriteGame(gameId, request)).thenReturn(favoriteGameModel);

        ResponseEntity<FavoriteGameModel> response = zeldaController.favoriteGame(gameId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


}
