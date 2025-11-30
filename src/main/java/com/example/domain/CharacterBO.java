package com.example.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import com.example.domain.entity.*;

public class CharacterBO {

    private List<GameCharacter> characterInformations;

    /* Genshin Impact */
    private static final Map<String, Integer> REGION_ORDER_GENSHIN_IMPACT = Map.of(
            "Genshin", 0,
            "God", 1,
            "Mondstadt", 2,
            "Liyue", 3,
            "Inazuma", 4,
            "Sumeru", 5,
            "Fontaine", 6,
            "Natlan", 7);

    public CharacterBO() {
        this.characterInformations = new ArrayList<>();
    }

    public CharacterBO(List<GameCharacter> characterInformationsFromRepo) {
        if (characterInformationsFromRepo == null) {
            this.characterInformations = new ArrayList<>();
        }
        this.characterInformations = new ArrayList<>(characterInformationsFromRepo);
    }

    public void sortCharacterInformations(String gameName) {
        switch (gameName) {
            case "Genshin Impact":
                Comparator<GameCharacter> comparator = Comparator
                        .comparing(
                                (GameCharacter c) -> REGION_ORDER_GENSHIN_IMPACT.get(c.getCharacterRegion()))
                        .thenComparing(GameCharacter::getCharacterQuality, Comparator.reverseOrder());
                this.characterInformations.sort(comparator);
                break;
            default:
                break;
        }
    }

    public List<GameCharacter> getCharacterInformations() {
        return this.characterInformations;
    }
}
