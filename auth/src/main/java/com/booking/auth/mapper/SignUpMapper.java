package com.booking.auth.mapper;

import com.booking.auth.dto.SignupRequestDto;
import com.booking.auth.entity.User;

public class SignUpMapper {
    public static User signUpRequestToEntity(SignupRequestDto requestDto) {
        return User.builder()
                .password(requestDto.getPassword())
                .email(requestDto.getEmail())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .build();
    }
}
