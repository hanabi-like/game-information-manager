package com.example.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacterBO {
    private String gameName;
    private Integer order;
    private String name;
    private Integer sex;
    private String region;
    private Integer quality;
}