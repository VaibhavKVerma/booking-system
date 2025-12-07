package com.booking.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {
    @Email
    @NotBlank
    @Size(min = 6, max = 50)
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;
}