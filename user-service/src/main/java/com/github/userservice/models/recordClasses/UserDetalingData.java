package com.github.userservice.models.recordClasses;

import com.github.userservice.models.User;

public record UserDetalingData(Long id, String name, Long age) {

    public UserDetalingData(User user){
        this(user.getId(), user.getName(), user.getAge());
    }
}
