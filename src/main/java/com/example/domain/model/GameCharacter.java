package com.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacter {
    private Integer order;
    private String name;
    private Integer sex;
    private String region;
    private Integer quality;
}