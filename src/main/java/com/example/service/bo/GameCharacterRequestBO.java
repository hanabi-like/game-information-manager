package com.example.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacterRequestBO {
    private String gameName;
    private String name;
    private Integer sex;
    private String region;
    private Integer quality;
    private String refName;
    private String insertPos;
}