package com.github.userservice.models.recordClasses;

import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
        @NotNull
        Long id,
        String name,
        Long age) {
}
