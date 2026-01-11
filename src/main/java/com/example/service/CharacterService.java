package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.repository.CharacterRepository;
import com.example.dto.CharacterInformationDTO;
import com.example.domain.CharacterBO;
import com.example.domain.entity.GameCharacter;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    private CharacterBO getCharacterBO() {
        List<GameCharacter> characterInformationsFromRepo = this.characterRepository.findAll();
        CharacterBO characterBo = new CharacterBO(characterInformationsFromRepo);
        return characterBo;
    }

    private List<GameCharacter> sortCharacterInformations(CharacterBO characterBo, String gameName) {
        characterBo.sortCharacterInformations(gameName);
        List<GameCharacter> sortedCharacterInformations = characterBo.getCharacterInformations();
        return sortedCharacterInformations;
    }

    private List<CharacterInformationDTO> getCharacterInformationDTO(List<GameCharacter> sortedCharacterInformations) {
        List<CharacterInformationDTO> characterInformationDTO = sortedCharacterInformations.stream()
                .map(CharacterInformationDTO::fromEntity).toList();
        return characterInformationDTO;
    }

    public List<CharacterInformationDTO> getSortedCharacterInformations(String gameName) {
        CharacterBO characterBo = getCharacterBO();
        List<GameCharacter> sortedCharacterInformations = sortCharacterInformations(characterBo, gameName);
        List<CharacterInformationDTO> characterInformationDTO = getCharacterInformationDTO(sortedCharacterInformations);
        return characterInformationDTO;
    }
}
