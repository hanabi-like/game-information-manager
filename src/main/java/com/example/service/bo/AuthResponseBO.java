package com.example.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseBO {
    private String token;
    private String tokenType;
    private Long expiresIn;
    private String username;
}