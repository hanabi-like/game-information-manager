package com.example.dto;

import com.example.domain.entity.GameCharacter;

public class CharacterInformationDTO {
    private Integer characterOrder;
    private String characterName;
    private int characterSex;
    private String characterPosition;
    private String characterRegion;
    private String characterQuality;

    public Integer getCharacterOrder() {
        return this.characterOrder;
    }

    public String getCharacterName() {
        return this.characterName;
    }

    public int getCharacterSex() {
        return this.characterSex;
    }

    public String getCharacterPosition() {
        return this.characterPosition;
    }

    public String getCharacterRegion() {
        return this.characterRegion;
    }

    public String getCharacterQuality() {
        return this.characterQuality;
    }

    public static CharacterInformationDTO fromEntity(GameCharacter gameCharacter) {
        CharacterInformationDTO characterInformationDTO = new CharacterInformationDTO();
        characterInformationDTO.characterOrder = gameCharacter.getCharacterOrder();
        characterInformationDTO.characterName = gameCharacter.getCharacterName();
        characterInformationDTO.characterSex = gameCharacter.getCharacterSex();
        characterInformationDTO.characterPosition = gameCharacter.getCharacterPosition();
        characterInformationDTO.characterRegion = gameCharacter.getCharacterRegion();
        characterInformationDTO.characterQuality = gameCharacter.getCharacterQuality();
        return characterInformationDTO;
    }
}
