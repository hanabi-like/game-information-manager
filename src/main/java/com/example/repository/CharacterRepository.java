package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.domain.entity.GameCharacter;

public interface CharacterRepository {
    Optional<GameCharacter> findByName(String characterName);

    List<GameCharacter> findAll();

    boolean existByName(String characterName);

    void save(GameCharacter gameCharacter);

    void deleteByName(String characterName);
}
