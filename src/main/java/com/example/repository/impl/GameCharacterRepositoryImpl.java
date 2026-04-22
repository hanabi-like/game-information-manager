package com.example.repository.impl;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import com.example.repository.GameCharacterRepository;
import com.example.domain.model.GameCharacter;
import com.example.repository.entity.GameCharacterEntity;
import com.example.repository.util.RedisKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

@Repository
@RequiredArgsConstructor
public class GameCharacterRepositoryImpl implements GameCharacterRepository {

    private final RedisTemplate<String, GameCharacterEntity> redisTemplate;

    @Override
    public boolean exist(String username, String gameName, String name) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(RedisKey.gameCharacterKey(username, gameName, name)));
    }

    @Override
    public void save(String username, String gameName, GameCharacter gameCharacter) {
        GameCharacterEntity gameCharacterEntity = toEntity(gameCharacter);
        redisTemplate.opsForValue().set(
                RedisKey.gameCharacterKey(username, gameName, gameCharacterEntity.getName()),
                gameCharacterEntity);
    }

    @Override
    public List<GameCharacter> findAll(String username, String gameName) {
        List<GameCharacter> result = new ArrayList<>();

        ScanOptions options = ScanOptions.scanOptions().match(RedisKey.gameCharacterPattern(username, gameName))
                .count(1000).build();

        try (var cursor = redisTemplate.getConnectionFactory().getConnection().scan(options)) {
            while (cursor.hasNext()) {
                byte[] keyBytes = cursor.next();
                String key = new String(keyBytes);
                GameCharacterEntity value = redisTemplate.opsForValue().get(key);
                Optional<GameCharacterEntity> optional = Optional.ofNullable(value);
                if (optional.isPresent()) {
                    result.add(toDomain(optional.get()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<GameCharacter> find(String username, String gameName, String name) {
        GameCharacterEntity obj = redisTemplate.opsForValue().get(RedisKey.gameCharacterKey(username, gameName, name));
        return Optional.ofNullable(obj).map(this::toDomain);
    }

    @Override
    public void delete(String username, String gameName, String name) {
        redisTemplate.delete(RedisKey.gameCharacterKey(username, gameName, name));
    }

    @Override
    public void update(String username, String gameName, GameCharacter gameCharacter) {
        save(username, gameName, gameCharacter);
    }

    private GameCharacterEntity toEntity(GameCharacter gameCharacter) {
        return GameCharacterEntity.builder()
                .order(gameCharacter.getOrder())
                .name(gameCharacter.getName())
                .sex(gameCharacter.getSex())
                .region(gameCharacter.getRegion())
                .quality(gameCharacter.getQuality())
                .build();
    }

    private GameCharacter toDomain(GameCharacterEntity gameCharacterEntity) {
        return GameCharacter.builder()
                .order(gameCharacterEntity.getOrder())
                .name(gameCharacterEntity.getName())
                .sex(gameCharacterEntity.getSex())
                .region(gameCharacterEntity.getRegion())
                .quality(gameCharacterEntity.getQuality())
                .build();
    }
}
