package com.example.repository;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
    boolean existGame(String username, String gameName);

    void saveGame(String username, String gameName);

    List<String> findAllGame(String username);

    Optional<String> findGame(String username, String gameName);

    void deleteGame(String username, String gameName);

    void updateGame(String username, String oldGameName, String newGameName);
}