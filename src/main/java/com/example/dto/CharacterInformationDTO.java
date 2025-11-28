package com.example.dto;

public class CharacterInformationDTO {
    private int characterId;
    private String characterName;
    private int characterSex;
    private String characterPosition;
    private String characterRegion;
    private String characterQuality;

    public int getCharacterId() {
        return this.characterId;
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
}
