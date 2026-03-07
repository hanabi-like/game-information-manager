package com.example.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacterOverviewBO {
    private String gameName;
    private List<RegionGameCharacterBO> regionGameCharacterBOList;
}