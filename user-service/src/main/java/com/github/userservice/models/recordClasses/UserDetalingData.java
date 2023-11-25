package com.github.userservice.models.recordClasses;

import com.github.userservice.models.UserModel;

public record UserDetalingData(Long id, String name, Long age) {

    public UserDetalingData(UserModel userModel){
        this(userModel.getId(), userModel.getName(), userModel.getAge());
    }
}
