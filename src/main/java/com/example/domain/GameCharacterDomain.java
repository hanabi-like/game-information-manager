package com.example.domain;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import com.example.domain.model.GameCharacter;
import com.example.domain.util.RegionDict;

public class GameCharacterDomain {
    public List<String> getGameRegion(List<GameCharacter> gameCharacter) {
        return gameCharacter.stream().map(GameCharacter::getRegion).distinct()
                .sorted(Comparator.comparingInt(r -> RegionDict.GENSHIN_IMPACT.getOrDefault(r, Integer.MAX_VALUE)))
                .toList();
    }

    public List<GameCharacter> sortSpecificGameCharacterByRegion(String region, List<GameCharacter> gameCharacter) {
        return gameCharacter.stream().filter(c -> c.getRegion().equals(region))
                .sorted(Comparator.comparingInt(GameCharacter::getOrder)).toList();
    }

    public List<GameCharacter> sortSpecificGameCharacter(String gameName, List<GameCharacter> gameCharacter) {
        switch (gameName) {
            case "Genshin Impact":
                return gameCharacter.stream()
                        .sorted(Comparator
                                .comparingInt((GameCharacter r) -> RegionDict.GENSHIN_IMPACT.getOrDefault(r.getRegion(),
                                        Integer.MAX_VALUE))
                                .thenComparing(GameCharacter::getOrder))
                        .toList();
            default:
                return Collections.emptyList();
        }
    }
}