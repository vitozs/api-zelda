package com.github.userservice.controllers;


import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    @Transactional
    public ResponseEntity<UserDetalingData> createUser(@RequestBody @Valid UserRegisterData data, UriComponentsBuilder uriComponentsBuilder){

        UserDetalingData userDto = userService.creatUser(data);

        URI uri = uriComponentsBuilder.path("user/create/{id}").buildAndExpand(userDto.id()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("update")
    @Transactional
    public ResponseEntity<UserDetalingData> updateUser(@RequestBody @Valid UserUpdateData dataUpdate, HttpServletRequest request){
        UserDetalingData userDto = userService.updateUser(dataUpdate, request);

        return ResponseEntity.ok(userDto);
    }



    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("profile")
    @Transactional
    public ResponseEntity<UserDetalingData> userProfile(HttpServletRequest request){
        UserDetalingData userDto = userService.getProfileUser(request);

        return ResponseEntity.ok(userDto);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUser(HttpServletRequest request){
        userService.deleteUser(request);

        return ResponseEntity.ok().build();
    }

}
