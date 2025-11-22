package com.booking.auth.service;

import com.booking.common.dto.auth.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public CustomUserDetails login(@RequestBody LoginRequestDto req) {
        log.info("Login Request: {}", req);
        if(StringUtils.isNotBlank(req.getEmail()) && StringUtils.isNotBlank(req.getPassword())) {
            return loginWithEmail(req);
        }
        if(StringUtils.isNotBlank(req.getUsername()) && StringUtils.isNotBlank(req.getPassword())) {
            return loginWithUsername(req);
        }
        log.info("Invalid Credentials");
        return null;
    }

    public CustomUserDetails loginWithUsername(@RequestBody LoginRequestDto req) {
        log.info("Login Request With Username: {}", req);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );
        log.info("Authenticated User By Username: {}", auth.getPrincipal());
        return (CustomUserDetails) auth.getPrincipal();
    }

    public CustomUserDetails loginWithEmail(@RequestBody LoginRequestDto req) {
        log.info("Login Request With Email: {}", req);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        log.info("Authenticated User By Email: {}", auth.getPrincipal());
        return (CustomUserDetails) auth.getPrincipal();
    }
}
