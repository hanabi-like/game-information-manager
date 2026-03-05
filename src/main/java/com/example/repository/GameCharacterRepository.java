package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.repository.entity.GameCharacterEntity;

public interface GameCharacterRepository {
    List<GameCharacterEntity> findAll();

    void save(GameCharacterEntity gameCharacterEntity);

    boolean existByName(String name);

    Optional<GameCharacterEntity> findByName(String name);

    void deleteByName(String name);
}
