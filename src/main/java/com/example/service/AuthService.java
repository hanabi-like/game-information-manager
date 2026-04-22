package com.example.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.model.User;
import com.example.repository.UserRepository;
import com.example.service.bo.AuthResponseBO;
import com.example.service.bo.LoginRequestBO;
import com.example.service.bo.RegisterRequestBO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestBO requestBO) {
        if (userRepository.exist(requestBO.getUsername())) {
            throw new RuntimeException("user already exist");
        }

        String passwordHash = passwordEncoder.encode(requestBO.getPassword());

        User user = User.builder()
                .username(requestBO.getUsername())
                .passwordHash(passwordHash)
                .build();

        userRepository.save(user);
    }

    public AuthResponseBO login(LoginRequestBO requestBO) {
        User user = userRepository.find(requestBO.getUsername())
                .orElseThrow(() -> new RuntimeException("invalid username or password"));

        if (!passwordEncoder.matches(requestBO.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("invalid username or password");
        }

        return AuthResponseBO.builder()
                .token("temporary-token")
                .tokenType("Bearer")
                .expiresIn(7200L)
                .username(user.getUsername())
                .build();
    }
}