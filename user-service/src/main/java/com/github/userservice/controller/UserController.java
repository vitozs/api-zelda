package com.github.userservice.controller;


import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("update")
    @Transactional
    public ResponseEntity<UserDetalingData> updateUser(@RequestBody @Valid UserUpdateData dataUpdate, HttpServletRequest request){
        UserDetalingData userDto = userService.updateUser(dataUpdate, request);

        return ResponseEntity.ok(userDto);
    }



    @GetMapping("profile")
    @Transactional
    public ResponseEntity<UserDetalingData> userProfile(HttpServletRequest request){
        UserDetalingData userDto = userService.getProfileUser(request);

        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUser(HttpServletRequest request){
        userService.deleteUser(request);

        return ResponseEntity.ok().build();
    }

}
