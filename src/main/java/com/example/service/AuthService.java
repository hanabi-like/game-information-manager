package com.example.service;

import org.springframework.stereotype.Service;

import com.example.service.bo.AuthResponseBO;
import com.example.service.bo.LoginRequestBO;
import com.example.service.bo.RegisterRequestBO;

@Service
public class AuthService {

    public void register(RegisterRequestBO requestBO) {
        return;
    }

    public AuthResponseBO login(LoginRequestBO requestBO) {
        return AuthResponseBO.builder()
                .token("temporary-token")
                .tokenType("Bearer")
                .expiresIn(7200L)
                .username(requestBO.getUsername())
                .build();
    }
}