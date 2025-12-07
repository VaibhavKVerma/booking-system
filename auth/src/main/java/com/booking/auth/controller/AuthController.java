package com.booking.auth.controller;

import com.booking.auth.dto.LoginRequestDto;
import com.booking.auth.dto.SignupRequestDto;
import com.booking.auth.service.AuthService;
import com.booking.common.dto.auth.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        UserResponseDto userResponseDto = authService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto) {
        UserResponseDto userResponseDto = authService.loginUser(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}