package com.example.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacterEntity {
    private Integer id;
    private Integer order;
    @Id
    private String name;
    private Integer sex;
    private String region;
    private Integer quality;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
