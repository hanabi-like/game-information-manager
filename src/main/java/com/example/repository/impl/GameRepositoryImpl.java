package com.example.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {
    private static final String KEY_PREFIX = "game";

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean existGame(String gameName) {
        return Boolean.TRUE.equals(
                redisTemplate.opsForSet().isMember(KEY_PREFIX, gameName));
    }

    @Override
    public void saveGame(String gameName) {
        redisTemplate.opsForSet().add(KEY_PREFIX, gameName);
    }

    @Override
    public List<String> findAllGame() {
        Set<String> gameSet = redisTemplate.opsForSet().members(KEY_PREFIX);
        return gameSet == null ? List.of() : List.copyOf(gameSet);
    }

    @Override
    public Optional<String> findGame(String gameName) {
        boolean exist = existGame(gameName);
        return exist ? Optional.of(gameName) : Optional.empty();
    }

    @Override
    public void deleteGame(String gameName) {
        redisTemplate.opsForSet().remove(KEY_PREFIX, gameName);
    }

    @Override
    public void updateGame(String oldGameName, String newGameName) {
        deleteGame(oldGameName);
        saveGame(newGameName);
    }
}
