package com.github.userservice.models.recordClasses;



public record UserUpdateData(
        String name,
        Long age,
        String email) {

}
