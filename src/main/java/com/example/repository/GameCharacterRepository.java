package com.example.repository;

import java.util.List;
import java.util.Optional;
import com.example.domain.model.GameCharacter;

public interface GameCharacterRepository {
    boolean exist(String username, String gameName, String name);

    void save(String username, String gameName, GameCharacter gameCharacter);

    List<GameCharacter> findAll(String username, String gameName);

    Optional<GameCharacter> find(String username, String gameName, String name);

    void delete(String username, String gameName, String name);

    void update(String username, String gameName, GameCharacter gameCharacter);
}
