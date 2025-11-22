package com.booking.common.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {
    private String username;
    private String email;
    private String password;
}