package com.github.zeldaservice.service;

import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.model.ZeldaGameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
@SpringBootTest
public class ZeldaServiceTest {

    ZeldaService zeldaService;
    ZeldaGameModel zeldaGameModel;

    @BeforeEach
    void setUp(){
        zeldaService = new ZeldaService();
    }

    @Test
    void verificaSeRetornaOJogoCertoPeloId() throws GameNotFoundException {
        zeldaGameModel = new ZeldaGameModel("The Legend of Zelda: Skyward Sword", " ", "Nintendo EAD", "Nintendo", " November 20, 2011", "5f6ce9d805615a85623ec2c8");

        Assertions.assertEquals(zeldaService.getGameById(zeldaGameModel.id()).getSingleData(), zeldaGameModel);

    }

    @Test
    void verificaSeRetornaListaComJogoZeldaPorNome() throws GameNotFoundException {
        zeldaGameModel = new ZeldaGameModel("The Legend of Zelda: Skyward Sword", " ", "Nintendo EAD", "Nintendo", " November 20, 2011", "5f6ce9d805615a85623ec2c8");

        Assertions.assertEquals(zeldaService.getGameByName("Skyward").getData(), Collections.singletonList(zeldaGameModel));

    }

    @Test
    void verificaSeRetornaTodosOsJogos(){
        List<ZeldaGameModel> jogos = zeldaService.getAllGames().getData();

        Assertions.assertEquals(20, jogos.size());
    }

    @Test
    void testaSeRetornaErroAoDigitarErradoOId(){
        GameNotFoundException e = Assertions.assertThrows(GameNotFoundException.class, ()->{
            zeldaService.getGameById("123").getSingleData();
        });

        Assertions.assertEquals(e.getMessage(), "Id do jogo digitado incorretamente, tente novamente mais tarde");
        Assertions.assertNotNull(e);

    }

    @Test
    void testaSeRetornaExceptionAoNaoEncontrarOJogo(){
        GameNotFoundException e = Assertions.assertThrows(GameNotFoundException.class, ()->{
            zeldaService.getGameByName("batata").getData();
        });

        Assertions.assertEquals(e.getMessage(), "O Jogo nao foi encontrado");
        Assertions.assertNotNull(e);
    }




}
