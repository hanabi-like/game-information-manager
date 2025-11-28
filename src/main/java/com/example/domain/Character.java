package com.example.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import com.example.dto.*;

public class Character {

    private List<CharacterInformationDTO> characterInformations;

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

    public Character() {
        this.characterInformations = new ArrayList<>();
    }

    public Character(List<CharacterInformationDTO> characterInformationsFromRepo) {
        if (characterInformationsFromRepo == null) {
            this.characterInformations = new ArrayList<>();
        }
        this.characterInformations = new ArrayList<>(characterInformationsFromRepo);
    }

    public void sortCharacterInformations(String gameName) {
        switch (gameName) {
            case "Genshin Impact":
                Comparator<CharacterInformationDTO> comparator = Comparator
                        .comparing(
                                (CharacterInformationDTO c) -> REGION_ORDER_GENSHIN_IMPACT.get(c.getCharacterRegion()))
                        .thenComparing(CharacterInformationDTO::getCharacterQuality, Comparator.reverseOrder());
                this.characterInformations.sort(comparator);
                break;
            default:
                break;
        }
    }

    public List<CharacterInformationDTO> getCharacterInformations() {
        return this.characterInformations;
    }
}
