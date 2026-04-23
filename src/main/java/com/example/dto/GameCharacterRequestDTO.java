package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacterRequestDTO {
    private String name;
    private Integer sex;
    private String region;
    private Integer quality;
    private String refName;
    private String insertPos;
}