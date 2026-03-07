package com.example.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.domain.model.GameCharacter;
import com.example.repository.entity.GameCharacterEntity;
import com.example.repository.impl.GameCharacterRepositoryImpl;

@DataRedisTest
@ContextConfiguration(classes = GameCharacterRepositoryTest.Config.class)
public class GameCharacterRepositoryTest {

    @TestConfiguration
    static class Config {

        @Bean
        public LettuceConnectionFactory redisConnectionFactory() {
            return new LettuceConnectionFactory("127.0.0.1", 6379);
        }

        @Bean
        public RedisTemplate<String, GameCharacterEntity> redisTemplate() {
            RedisTemplate<String, GameCharacterEntity> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory());
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            return template;
        }

        @Bean
        public GameCharacterRepository gameCharacterRepository(
                RedisTemplate<String, GameCharacterEntity> redisTemplate) {
            return new GameCharacterRepositoryImpl(redisTemplate);
        }
    }

    @Autowired
    private GameCharacterRepository gameCharacterRepository;

    private String inputGameName = "Genshin Impact";
    private List<GameCharacter> input;

    @BeforeEach
    void setup() {
        input = Arrays.asList(
                GameCharacter.builder().order(4).name("a").sex(0).region("region0").quality(5).build(),
                GameCharacter.builder().order(3).name("b").sex(1).region("region1").quality(4).build(),
                GameCharacter.builder().order(2).name("c").sex(0).region("region2").quality(5).build(),
                GameCharacter.builder().order(1).name("d").sex(1).region("region3").quality(4).build(),
                GameCharacter.builder().order(0).name("e").sex(0).region("region4").quality(5).build());
        for (int i = 0; i < input.size(); ++i) {
            gameCharacterRepository.save(inputGameName, input.get(i));
        }
    }

    @AfterEach
    void cleanup() {
        for (int i = 0; i < input.size(); ++i) {
            String name = input.get(i).getName();
            gameCharacterRepository.delete(inputGameName, name);
        }
    }

    @Test
    public void shouldAsExpectationWhenExecuteCRUD() {
        List<GameCharacter> gameCharacterList = gameCharacterRepository.findAll(inputGameName);
        for (int i = 0; i < gameCharacterList.size(); ++i) {
            Integer order = gameCharacterList.get(i).getOrder();
            String name = gameCharacterList.get(i).getName();
            Integer sex = gameCharacterList.get(i).getSex();
            String region = gameCharacterList.get(i).getRegion();
            Integer quality = gameCharacterList.get(i).getQuality();
            System.out.println(" order: " + order + " name: " + name + " sex: " + sex + " region: "
                    + region + " quality: " + quality);
        }
        for (GameCharacter i : input) {
            GameCharacter gameCharacter = gameCharacterRepository.find(inputGameName, i.getName()).get();
            assertEquals(i.getOrder(), gameCharacter.getOrder());
            assertEquals(i.getName(), gameCharacter.getName());
            assertEquals(i.getSex(), gameCharacter.getSex());
            assertEquals(i.getRegion(), gameCharacter.getRegion());
            assertEquals(i.getQuality(), gameCharacter.getQuality());
        }
    }
}
