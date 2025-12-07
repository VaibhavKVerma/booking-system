package com.booking.auth.mapper;

import com.booking.auth.entity.User;
import com.booking.common.dto.auth.UserResponseDto;

public class UserMapper {
    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
