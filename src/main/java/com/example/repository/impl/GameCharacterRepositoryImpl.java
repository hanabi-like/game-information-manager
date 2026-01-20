package com.example.repository.impl;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import com.example.repository.GameCharacterRepository;
import com.example.repository.entity.GameCharacterEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

@Repository
@RequiredArgsConstructor
public class GameCharacterRepositoryImpl implements GameCharacterRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_PREFIX = "gamecharacter:";

    @Override
    public List<GameCharacterEntity> findAll() {
        List<GameCharacterEntity> result = new ArrayList<>();

        ScanOptions options = ScanOptions.scanOptions().match(KEY_PREFIX + "*").count(1000).build();

        try (var cursor = redisTemplate.getConnectionFactory().getConnection().scan(options)) {
            while (cursor.hasNext()) {
                byte[] keyBytes = cursor.next();
                String key = new String(keyBytes);
                Object value = redisTemplate.opsForValue().get(key);
                Optional<GameCharacterEntity> optional = Optional.ofNullable((GameCharacterEntity) value);
                if (optional.isPresent()) {
                    result.add(optional.get());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(GameCharacterEntity gameCharacterEntity) {
        redisTemplate.opsForValue().set(KEY_PREFIX + gameCharacterEntity.getName(), gameCharacterEntity);
    }

    @Override
    public boolean existByName(String name) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(KEY_PREFIX + name));
    }

    @Override
    public Optional<GameCharacterEntity> findByName(String name) {
        Object obj = redisTemplate.opsForValue().get(KEY_PREFIX + name);
        return Optional.ofNullable((GameCharacterEntity) obj);
    }

    @Override
    public void deleteByName(String name) {
        redisTemplate.delete(KEY_PREFIX + name);
    }
}
