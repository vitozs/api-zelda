package com.github.zeldaservice.service;

import com.github.zeldaservice.Repository.FavoriteRepository;
import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.infra.exception.SomethingWentWrongException;
import com.github.zeldaservice.infra.security.SecurityZeldaFilter;
import com.github.zeldaservice.model.RequestModel;
import com.github.zeldaservice.model.SingleRequestModel;
import com.github.zeldaservice.model.favoriteModel.FavoriteGameModel;
import com.github.zeldaservice.model.favoriteModel.ReturnFavoritesModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@AllArgsConstructor // pra injetar com o all args vc precisa colkocar todos os campos como private final.
public class ZeldaService {

//    @Autowired // evite usar autowired porque é véio e não é recomendado pelo pessoal da mola. Use a injeção via construtor ou @AllArgsConstructor (que é um constutor gerado pra todas os fields)
    private /*final*/ TokenService tokenService;

//    @Autowired
    private /*final*/  FavoriteRepository favoriteRepository;


    // uma das opções: use um construtor....
//    public ZeldaService(TokenService tokenService, FavoriteRepository favoriteRepository) {
//        this.tokenService = tokenService;
//        this.favoriteRepository = favoriteRepository;
//    }

    private SecurityZeldaFilter securityZeldaFilter = new SecurityZeldaFilter(); // se for um bean (@Service)

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

        favoriteGameModel.setIdUsuario(tokenService.getIdUser(tokenJWT));
        favoriteGameModel.setId_jogo(favoriteRepository.findById(Long.parseLong(id)).get().getId_jogo());

        return  favoriteRepository.save(favoriteGameModel);
    }

    public ReturnFavoritesModel getFavoriteGames(HttpServletRequest request) {
        String tokenJWT = securityZeldaFilter.recoverToken(request);

        ReturnFavoritesModel returnFavoritesModel = new ReturnFavoritesModel();

        Long idUser = tokenService.getIdUser(tokenJWT);

        returnFavoritesModel.setUserModel(favoriteRepository.findByname(idUser));
        returnFavoritesModel.setFavoriteGamesList(favoriteRepository.findByGames(idUser));

        return returnFavoritesModel;
    }
}
