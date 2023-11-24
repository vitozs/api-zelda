package com.github.zeldaservice.service;

import com.github.zeldaservice.exception.GameNotFoundException;
import com.github.zeldaservice.exception.SomethingWentWrongException;
import com.github.zeldaservice.model.RequestModel;
import com.github.zeldaservice.model.SingleRequestModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
public class ZeldaService {

    public RequestModel getAllGames() throws SomethingWentWrongException{
        try {
            RequestModel zeldaResponse = WebClient.create("https://zelda.fanapis.com/api/games")
                    .get()
                    .retrieve()
                    .bodyToMono(RequestModel.class)
                    .block();
            return zeldaResponse;

        }catch (WebClientException e){
            throw new SomethingWentWrongException("Algo deu errado! Tente novamente mais tarde");
        }

    }

    public RequestModel getGameByName(String name) throws GameNotFoundException {

        try {
            RequestModel zeldaResponse = WebClient.create("https://zelda.fanapis.com/api/games?name=" + name)
                    .get()
                    .retrieve()
                    .bodyToMono(RequestModel.class)
                    .block();
            if (zeldaResponse.getCount() == 0){
                throw new GameNotFoundException("O Jogo nao foi encontrado");
            }else{
                return zeldaResponse;
            }
        }catch (WebClientException e){
            throw new SomethingWentWrongException("Algo deu errado, tente novamente mais tarde");
        }





    }

    public SingleRequestModel getGameById(String id) throws GameNotFoundException {
        try {
            SingleRequestModel zeldaResponse = WebClient.create("https://zelda.fanapis.com/api/games/" + id)
                    .get()
                    .retrieve()
                    .bodyToMono(SingleRequestModel.class)
                    .block();
            return zeldaResponse;
        }catch (WebClientException e){
            throw new GameNotFoundException("Id do jogo digitado incorretamente, tente novamente mais tarde");
        }

    }
}
