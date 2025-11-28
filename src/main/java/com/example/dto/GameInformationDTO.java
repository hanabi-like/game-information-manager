package com.example.dto;

public class GameInformationDTO {
    private int gameId;
    private String gameName;
    private CharacterInformationDTO characterInformation;

    public int getGameId() {
        return this.gameId;
    }

    public String getGameName() {
        return this.gameName;
    }

    public CharacterInformationDTO getCharacterInformation() {
        return this.characterInformation;
    }
}
