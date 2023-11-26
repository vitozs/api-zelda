package com.github.userservice.models.recordClasses;

import jakarta.validation.constraints.*;

public record UserRegisterData(
        @NotBlank
        String name,
        @NotNull
        @Min(12)
        @Max(80)
        Long age,
        @Email
        String email,
        @NotBlank
        String password
) {

}
