package com.github.userservice.controller;


import com.github.userservice.models.User;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("list")
    @Transactional
    public ResponseEntity<List<UserDetalingData>> listUsers() {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("get/{userId}")
    @Transactional
    public ResponseEntity<UserDetalingData> getUserById(@PathVariable("userId") Long userId) {
        Optional<UserDetalingData> user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("update")
    @Transactional
    public ResponseEntity<UserDetalingData> updateUser(@RequestBody @Valid UserUpdateData dataUpdate){
        UserDetalingData userDto = userService.updateUser(dataUpdate);

        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("delete/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteUser( @PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

}
