package com.example.controller;

import com.example.dto.AuthResponseDTO;
import com.example.dto.LoginRequestDTO;
import com.example.dto.RegisterRequestDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        AuthResponseDTO responseDTO = AuthResponseDTO.builder()
                .token("temporary-token")
                .tokenType("Bearer")
                .expiresIn(7200L)
                .username(requestDTO.getUsername())
                .build();

        return ResponseEntity.ok(responseDTO);
    }

}