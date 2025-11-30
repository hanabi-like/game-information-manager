package com.example.domain.entity;

public class GameCharacter {
    private int characterId;
    private String characterName;
    private int characterSex;
    private String characterPosition;
    private String characterRegion;
    private String characterQuality;

    public GameCharacter() {
    }

    public GameCharacter(int id, String name, int sex, String position, String region, String quality) {
        this.characterId = id;
        this.characterName = name;
        this.characterSex = sex;
        this.characterPosition = position;
        this.characterRegion = region;
        this.characterQuality = quality;
    }

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
