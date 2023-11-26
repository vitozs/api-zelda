package com.github.userservice.controller;

import com.github.userservice.infra.security.TokenJWTData;
import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.AutenticationData;
import com.github.userservice.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager; // do proprio spring

    @Autowired
    private TokenService tokenService;
    @PostMapping("user")
    public ResponseEntity login(@RequestBody @Valid AutenticationData data){
        var authenticationTokentoken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication authentication = authenticationManager.authenticate(authenticationTokentoken);

        var tokenJWT = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return  ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}
