package com.example.repository.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import com.example.repository.GameRepository;
import com.example.repository.util.RedisKey;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean existGame(String username, String gameName) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(RedisKey.gameKey(username, gameName)));
    }

    @Override
    public void saveGame(String username, String gameName) {
        redisTemplate.opsForValue().set(RedisKey.gameKey(username, gameName), gameName);
    }

    @Override
    public List<String> findAllGame(String username) {
        List<String> result = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions().match(RedisKey.gamePattern(username)).count(1000).build();

        try (var cursor = redisTemplate.getConnectionFactory().getConnection().scan(options)) {
            while (cursor.hasNext()) {
                String key = new String(cursor.next());
                String prefix = RedisKey.userKey(username) + ":";
                String suffix = key.substring(prefix.length());
                if (!suffix.contains(":")) {
                    result.add(suffix);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<String> findGame(String username, String gameName) {
        String game = redisTemplate.opsForValue().get(RedisKey.gameKey(username, gameName));
        return Optional.ofNullable(game);
    }

    @Override
    public void deleteGame(String username, String gameName) {
        redisTemplate.delete(RedisKey.gameKey(username, gameName));
    }

    @Override
    public void updateGame(String username, String oldGameName, String newGameName) {
        deleteGame(username, oldGameName);
        saveGame(username, newGameName);
    }
}
