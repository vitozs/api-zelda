package com.github.zeldaservice.service;

import com.github.zeldaservice.Repository.FavoriteRepository;
import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.infra.exception.SomethingWentWrongException;
import com.github.zeldaservice.infra.security.SecurityZeldaFilter;
import com.github.zeldaservice.model.RequestModel;
import com.github.zeldaservice.model.SingleRequestModel;
import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
public class ZeldaService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FavoriteRepository favoriteRepository;

    private SecurityZeldaFilter securityZeldaFilter = new SecurityZeldaFilter();

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

    public FavoriteGameModel saveFavoriteGame(String id, HttpServletRequest request) {
        String tokenJWT = securityZeldaFilter.recoverToken(request);

        FavoriteGameModel favoriteGameModel = new FavoriteGameModel();

        favoriteGameModel.setId_user(tokenService.getIdUser(tokenJWT));
        favoriteGameModel.setId_game(favoriteRepository.findByid_game(id));

        return  favoriteRepository.save(favoriteGameModel);
    }
}
