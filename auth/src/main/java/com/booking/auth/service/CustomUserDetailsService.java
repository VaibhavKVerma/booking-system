package com.booking.auth.service;

import com.booking.auth.entity.User;
import com.booking.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        User user;
        log.info("username: {}", usernameOrEmail);
        if (usernameOrEmail.contains("@")) {
            log.info("Searching by email: {}", usernameOrEmail);
            user = userRepository.findByEmail(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found by email"));
        } else {
            log.info("Searching by username: {}", usernameOrEmail);
            user = userRepository.findByUsername(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found by username"));
        }

        log.info("user: {}", user);
        return new CustomUserDetails(user);
    }
}

