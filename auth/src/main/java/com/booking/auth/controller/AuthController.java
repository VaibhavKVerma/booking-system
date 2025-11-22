package com.booking.auth.controller;

import com.booking.common.dto.auth.LoginRequestDto;
import com.booking.common.dto.auth.LoginResponseDto;
import com.booking.auth.service.AuthService;
import com.booking.auth.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
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


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto req) {
        CustomUserDetails userLoginDetails = authService.login(req);

        LoginResponseDto response = LoginResponseDto.builder()
                .userId(userLoginDetails.getUserId())
                .build();

        return ResponseEntity.ok(response);
    }
}