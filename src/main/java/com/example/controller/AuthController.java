package com.example.controller;

import com.example.dto.AuthResponseDTO;
import com.example.dto.LoginRequestDTO;
import com.example.dto.RegisterRequestDTO;
import com.example.service.AuthService;
import com.example.service.bo.AuthResponseBO;
import com.example.service.bo.LoginRequestBO;
import com.example.service.bo.RegisterRequestBO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO requestDTO) {
        authService.register(toRegisterRequestBO(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        AuthResponseBO responseBO = authService.login(toLoginRequestBO(requestDTO));
        return ResponseEntity.ok(toAuthResponseDTO(responseBO));
    }

    private RegisterRequestBO toRegisterRequestBO(RegisterRequestDTO requestDTO) {
        return RegisterRequestBO.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .build();
    }

    private LoginRequestBO toLoginRequestBO(LoginRequestDTO requestDTO) {
        return LoginRequestBO.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .build();
    }

    private AuthResponseDTO toAuthResponseDTO(AuthResponseBO responseBO) {
        return AuthResponseDTO.builder()
                .token(responseBO.getToken())
                .tokenType(responseBO.getTokenType())
                .expiresIn(responseBO.getExpiresIn())
                .username(responseBO.getUsername())
                .build();
    }
}