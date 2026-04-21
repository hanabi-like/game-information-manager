package com.example.repository;

import java.util.Optional;

import com.example.domain.model.User;

public interface UserRepository {
    boolean exist(String username);

    void save(User user);

    Optional<User> find(String username);
}