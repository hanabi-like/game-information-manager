package com.example.repository;

import java.util.List;
import java.util.Optional;
import com.example.domain.model.GameCharacter;

public interface GameCharacterRepository {
    boolean exist(String gameName, String name);

    void save(String gameName, GameCharacter gameCharacter);

    List<GameCharacter> findAll(String gameName);

    Optional<GameCharacter> find(String gameName, String name);

    void delete(String gameName, String name);

    void update(String gameName, GameCharacter gameCharacter);
}