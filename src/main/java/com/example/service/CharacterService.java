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

    public List<CharacterInformationDTO> sortCharacterInformations(String gameName) {
        List<GameCharacter> characterInformationsFromRepo = this.characterRepository.findAll();
        CharacterBO characterBo = new CharacterBO(characterInformationsFromRepo);
        characterBo.sortCharacterInformations(gameName);
        List<GameCharacter> sortedCharacterInformations = characterBo.getCharacterInformations();
        List<CharacterInformationDTO> characterInformationDTO = sortedCharacterInformations.stream()
                .map(CharacterInformationDTO::fromEntity).toList();
        return characterInformationDTO;
    }
}
