package com.github.zeldaservice.model;

import com.github.zeldaservice.exception.GameNotFoundException;
import com.github.zeldaservice.service.ZeldaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SingleRequestModelTest {

    ZeldaService zeldaService;

    SingleRequestModel singleRequestModel;

    @BeforeEach
    void setUp(){
        zeldaService = new ZeldaService();
    }

    @Test
    void verificaMetodosDaClasseSingleRequestModel() throws GameNotFoundException {

        SingleRequestModel response = zeldaService.getGameById("5f6ce9d805615a85623ec2c8");
        ZeldaGameModel zeldaGameModel = new ZeldaGameModel("The Legend of Zelda: Skyward Sword", " ", "Nintendo EAD", "Nintendo", " November 20, 2011", "5f6ce9d805615a85623ec2c8");

        Assertions.assertEquals(true, response.isSuccess());
        Assertions.assertEquals(zeldaGameModel, response.getSingleData());

    }



}
