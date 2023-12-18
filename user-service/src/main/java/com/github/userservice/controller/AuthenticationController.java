package com.github.userservice.controller;

import com.github.userservice.infra.security.TokenJWTData;
import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.AutenticationData;
import com.github.userservice.repository.UserRepository;
import com.github.userservice.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    @PostMapping("user")
    public ResponseEntity<TokenJWTData>login(@RequestBody @Valid AutenticationData data){
        var authenticationTokentoken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication authentication = authenticationManager.authenticate(authenticationTokentoken);

        var tokenJWT = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return  ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }

    @PostMapping("authentication")
    @Transactional
    public ResponseEntity<Boolean> authentication(@RequestBody MultiValueMap<String, String> data) {
        var email = data.getFirst("email");
        boolean emailExists = userRepository.existsByemail(email);
        return ResponseEntity.ok(emailExists);
    }

}
