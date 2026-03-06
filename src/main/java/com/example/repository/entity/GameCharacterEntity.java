package com.example.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacterEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer order;
    @Id
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer sex;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private Integer quality;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
