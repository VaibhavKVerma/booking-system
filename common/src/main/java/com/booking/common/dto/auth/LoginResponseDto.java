package com.booking.common.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDto {
    private String username;
    private String userId;
    private String refreshToken;
    private String accessToken;
}
