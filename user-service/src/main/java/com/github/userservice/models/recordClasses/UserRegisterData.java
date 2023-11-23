package com.github.userservice.models.recordClasses;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterData(
        @NotBlank
        String name,
        @NotNull
        @Min(12)
        @Max(80)
        Long age
) {
}
