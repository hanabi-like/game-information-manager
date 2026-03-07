package com.example.repository;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
    boolean existGame(String gameName);

    void saveGame(String gameName);

    List<String> findAllGame();

    Optional<String> findGame(String gameName);

    void deleteGame(String gameName);

    void updateGame(String oldGameName, String newGameName);
}