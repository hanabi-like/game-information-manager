package com.example.repository.impl;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.model.User;
import com.example.repository.UserRepository;
import com.example.repository.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String KEY_PREFIX = "auth:user:";

    private final RedisTemplate<String, UserEntity> redisTemplate;

    @Override
    public boolean exist(String username) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(KEY_PREFIX + username));
    }

    @Override
    public void save(User user) {
        UserEntity entity = toEntity(user);
        redisTemplate.opsForValue().set(KEY_PREFIX + entity.getUsername(), entity);
    }

    @Override
    public Optional<User> find(String username) {
        UserEntity entity = redisTemplate.opsForValue().get(KEY_PREFIX + username);
        return Optional.ofNullable(entity).map(this::toDomain);
    }

    private UserEntity toEntity(User user) {
        return UserEntity.builder()
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .build();
    }

    private User toDomain(UserEntity entity) {
        return User.builder()
                .username(entity.getUsername())
                .passwordHash(entity.getPasswordHash())
                .build();
    }
}