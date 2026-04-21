package com.example.service;

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

    public void register(RegisterRequestBO requestBO) {
        User user = User.builder()
                .username(requestBO.getUsername())
                .password(requestBO.getPassword())
                .build();
        userRepository.save(user);
    }

    public AuthResponseBO login(LoginRequestBO requestBO) {
        User user = userRepository.find(requestBO.getUsername()).orElse(null);
        return AuthResponseBO.builder()
                .token("temporary-token")
                .tokenType("Bearer")
                .expiresIn(7200L)
                .username(user == null ? requestBO.getUsername() : user.getUsername())
                .build();
    }
}