package com.example.repository.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
@RedisHash("gamecharacter")
public class GameCharacterEntity {
    private Integer id;
    private Integer order;
    @Id
    private String name;
    private Integer sex;
    private String region;
    private String quality;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
