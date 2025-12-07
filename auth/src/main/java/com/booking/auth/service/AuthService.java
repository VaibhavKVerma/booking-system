package com.booking.auth.service;

import com.booking.auth.dto.LoginRequestDto;
import com.booking.auth.dto.SignupRequestDto;
import com.booking.auth.entity.User;
import com.booking.auth.mapper.SignUpMapper;
import com.booking.auth.mapper.UserMapper;
import com.booking.auth.repository.UserRepository;
import com.booking.common.dto.auth.UserResponseDto;
import com.booking.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto registerUser(SignupRequestDto requestDto) {
        User user = SignUpMapper.signUpRequestToEntity(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public UserResponseDto loginUser(LoginRequestDto requestDto) {
        User user = loadUserByUsername(requestDto.getEmail());
        if (user == null) {
            throw new NotFoundException("No email in the database: " + requestDto.getEmail());
        }
        boolean matches = passwordEncoder.matches(requestDto.getPassword(), user.getPassword());
        if (!matches) {
            throw new NotFoundException("Incorrect Password for email : " + user.getEmail());
        }
        return UserMapper.toUserResponseDto(user);
    }
}
