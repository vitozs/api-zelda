package com.github.zeldaservice.model;

import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.service.ZeldaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestModelTest {

    ZeldaService zeldaService;

    RequestModel requestModel;
    @BeforeEach
    void setUp(){
        zeldaService = new ZeldaService();
    }

    @Test
    void testaSeRetornaValoresCorretosDoRequestModel() throws GameNotFoundException {
        requestModel = zeldaService.getGameByName("Skyward");

        Assertions.assertEquals(true, requestModel.isSuccess());
        Assertions.assertEquals(1, requestModel.getCount());
        Assertions.assertEquals(requestModel.getData().size(), 1);

    }

}
