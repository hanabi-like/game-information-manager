package com.example.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.repository.GameRepository;
import com.example.repository.GameCharacterRepository;
import com.example.domain.GameCharacterDomain;
import com.example.domain.model.GameCharacter;
import com.example.service.bo.GameCharacterBO;
import com.example.service.bo.GameCharacterOverviewBO;
import com.example.service.bo.RegionGameCharacterBO;

@Service
@RequiredArgsConstructor
public class GameCharacterService {
    private final GameRepository gameRepository;
    private final GameCharacterRepository gameCharacterRepository;
    private final GameCharacterDomain gameCharacterDomain = new GameCharacterDomain();

    public void saveGameCharacter(String gameName, GameCharacterBO gameCharacterBO) {
        GameCharacter gameCharacter = toDomain(gameCharacterBO);
        if (!gameRepository.existGame(gameName)) {
            gameRepository.saveGame(gameName);
            gameCharacterRepository.save(gameName, gameCharacter);
            return;
        } else if (!gameCharacterRepository.exist(gameName, gameCharacter.getName())) {
            gameCharacterRepository.save(gameName, gameCharacter);
            return;
        }
        System.out.println("exist");
        return;
    }

    public void updateGame(String oldGameName, String newGameName) {
        if (!gameRepository.existGame(oldGameName)) {
            System.out.println("not exist");
            return;
        }
        List<GameCharacter> gameCharacterList = gameCharacterRepository.findAll(oldGameName);
        for (var c : gameCharacterList) {
            gameCharacterRepository.delete(oldGameName, c.getName());
        }
        gameRepository.updateGame(oldGameName, newGameName);
        for (var c : gameCharacterList) {
            gameCharacterRepository.save(newGameName, c);
        }
        return;
    }

    public void updateGameCharacter(String gameName, GameCharacterBO oldGameCharacterBO,
            GameCharacterBO newGameCharacterBO) {
        GameCharacter oldGameCharacter = toDomain(oldGameCharacterBO);
        GameCharacter newGameCharacter = toDomain(newGameCharacterBO);
        if (!gameRepository.existGame(gameName)
                || !gameCharacterRepository.exist(gameName, oldGameCharacter.getName())) {
            System.out.println("not exist");
            return;
        }
        gameCharacterRepository.update(gameName, newGameCharacter);
        return;
    }

    public void deleteGame(String gameName) {
        if (!gameRepository.existGame(gameName)) {
            System.out.println("not exist");
            return;
        }
        List<GameCharacter> gameCharacterList = gameCharacterRepository.findAll(gameName);
        for (var c : gameCharacterList) {
            gameCharacterRepository.delete(gameName, c.getName());
        }
        gameRepository.deleteGame(gameName);
        return;
    }

    public void deleteGameCharacter(String gameName, GameCharacterBO gameCharacterBO) {
        GameCharacter gameCharacter = toDomain(gameCharacterBO);
        if (!gameRepository.existGame(gameName)
                || !gameCharacterRepository.exist(gameName, gameCharacter.getName())) {
            System.out.println("not exist");
            return;
        }
        gameCharacterRepository.delete(gameName, gameCharacter.getName());
        return;
    }

    public GameCharacterOverviewBO displaySpecificGameCharacter(String gameName) {
        List<GameCharacter> gameCharacterListFromRepo = gameCharacterRepository.findAll(gameName);
        List<GameCharacter> gameCharacterList = gameCharacterDomain.sortSpecificGameCharacter(gameName,
                gameCharacterListFromRepo);
        return toBO(gameName, gameCharacterList);
    }

    private GameCharacter toDomain(GameCharacterBO gameCharacterBO) {
        return GameCharacter.builder().order(gameCharacterBO.getOrder()).name(gameCharacterBO.getName())
                .sex(gameCharacterBO.getSex()).region(gameCharacterBO.getRegion()).quality(gameCharacterBO.getQuality())
                .build();
    }

    private GameCharacterOverviewBO toBO(String gameName, List<GameCharacter> gameCharacterList) {
        Map<String, List<GameCharacter>> regionMap = gameCharacterList.stream()
                .collect(Collectors.groupingBy(GameCharacter::getRegion, LinkedHashMap::new, Collectors.toList()));
        List<RegionGameCharacterBO> regionGameCharacterBOList = regionMap.entrySet().stream()
                .map(entry -> RegionGameCharacterBO.builder().region(entry.getKey())
                        .nameList(entry.getValue().stream().map(GameCharacter::getName).toList()).build())
                .toList();
        return GameCharacterOverviewBO.builder().gameName(gameName).regionGameCharacterBOList(regionGameCharacterBOList)
                .build();
    }
}