package com.booking.common.dto.auth;

import com.booking.common.enums.auth.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private Instant createdDate;
    private Instant modifiedDate;
}
