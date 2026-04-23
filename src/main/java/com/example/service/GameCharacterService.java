package com.example.service;

import java.util.Comparator;
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
import com.example.service.bo.GameCharacterRequestBO;
import com.example.service.bo.GameCharacterResponseBO;
import com.example.service.bo.GameCharacterRegionResponseBO;

@Service
@RequiredArgsConstructor
public class GameCharacterService {
    private final GameRepository gameRepository;
    private final GameCharacterRepository gameCharacterRepository;
    private final GameCharacterDomain gameCharacterDomain = new GameCharacterDomain();

    public void saveGameCharacter(String username, String gameName, GameCharacterRequestBO gameCharacterRequestBO) {
        if (!gameRepository.existGame(username, gameName))
            gameRepository.saveGame(username, gameName);

        if (gameCharacterRepository.exist(username, gameName, gameCharacterRequestBO.getName())) {
            System.out.println("exist");
            return;
        }

        List<GameCharacter> gameCharacterByRegionList = gameCharacterRepository.findAll(username, gameName)
                .stream()
                .filter(c -> c.getRegion().equals(gameCharacterRequestBO.getRegion()))
                .sorted(Comparator.comparingInt(GameCharacter::getOrder))
                .toList();

        Integer newOrder = resolveNewOrder(gameCharacterByRegionList, gameCharacterRequestBO);

        for (GameCharacter gc : gameCharacterByRegionList) {
            if (gc.getOrder() >= newOrder) {
                gameCharacterRepository.save(username, gameName, rebuildWithNewOrder(gc, gc.getOrder() + 1));
            }
        }

        GameCharacter newGameCharacter = toDomain(gameCharacterRequestBO, newOrder);
        gameCharacterRepository.save(username, gameName, newGameCharacter);
    }

    public void updateGame(String username, String oldGameName, String newGameName) {
        if (!gameRepository.existGame(username, oldGameName)) {
            System.out.println("not exist");
            return;
        }
        List<GameCharacter> gameCharacterList = gameCharacterRepository.findAll(username, oldGameName);
        for (var c : gameCharacterList) {
            gameCharacterRepository.delete(username, oldGameName, c.getName());
        }
        gameRepository.updateGame(username, oldGameName, newGameName);
        for (var c : gameCharacterList) {
            gameCharacterRepository.save(username, newGameName, c);
        }
        return;
    }

    public void deleteGame(String username, String gameName) {
        if (!gameRepository.existGame(username, gameName)) {
            System.out.println("not exist");
            return;
        }
        List<GameCharacter> gameCharacterList = gameCharacterRepository.findAll(username, gameName);
        for (var c : gameCharacterList) {
            gameCharacterRepository.delete(username, gameName, c.getName());
        }
        gameRepository.deleteGame(username, gameName);
        return;
    }

    public void deleteGameCharacter(String username, String gameName, GameCharacterRequestBO gameCharacterRequestBO) {
        if (!gameRepository.existGame(username, gameName)
                || !gameCharacterRepository.exist(username, gameName, gameCharacterRequestBO.getName())) {
            System.out.println("not exist");
            return;
        }
        gameCharacterRepository.delete(username, gameName, gameCharacterRequestBO.getName());
        return;
    }

    public GameCharacterResponseBO getSpecificGameCharacter(String username, String gameName) {
        List<GameCharacter> gameCharacterListFromRepo = gameCharacterRepository.findAll(username, gameName);
        List<GameCharacter> gameCharacterList = gameCharacterDomain.sortSpecificGameCharacter(gameName,
                gameCharacterListFromRepo);
        return toResponseBO(gameName, gameCharacterList);
    }

    List<String> getSpecificGameRegion(String username, String gameName) {
        List<GameCharacter> gameCharacterListFromRepo = gameCharacterRepository.findAll(username, gameName);
        return gameCharacterDomain.getGameRegion(gameCharacterListFromRepo);
    }

    public GameCharacterRegionResponseBO getSpecificGameCharacterByRegion(String username, String gameName,
            String region) {
        List<GameCharacter> gameCharacterListFromRepo = gameCharacterRepository.findAll(username, gameName);
        List<GameCharacter> gameCharacterList = gameCharacterDomain.sortSpecificGameCharacterByRegion(region,
                gameCharacterListFromRepo);
        return toRegionResponseBO(gameName, region, gameCharacterList);
    }

    private GameCharacter toDomain(GameCharacterRequestBO gameCharacterRequestBO, Integer order) {
        return GameCharacter.builder()
                .order(order)
                .name(gameCharacterRequestBO.getName())
                .sex(gameCharacterRequestBO.getSex())
                .region(gameCharacterRequestBO.getRegion())
                .quality(gameCharacterRequestBO.getQuality())
                .build();
    }

    private GameCharacterResponseBO toResponseBO(String gameName, List<GameCharacter> gameCharacterList) {
        Map<String, List<GameCharacter>> regionMap = gameCharacterList.stream()
                .collect(Collectors.groupingBy(GameCharacter::getRegion, LinkedHashMap::new, Collectors.toList()));
        List<GameCharacterRegionResponseBO> gameCharacterRegionResponseBOList = regionMap.entrySet().stream()
                .map(entry -> GameCharacterRegionResponseBO.builder().region(entry.getKey())
                        .nameList(entry.getValue().stream().map(GameCharacter::getName).toList()).build())
                .toList();
        return GameCharacterResponseBO.builder().gameName(gameName)
                .gameCharacterRegionResponseBOList(gameCharacterRegionResponseBOList)
                .build();
    }

    private GameCharacterRegionResponseBO toRegionResponseBO(String gameName, String region,
            List<GameCharacter> gameCharacterList) {
        List<String> nameList = gameCharacterList.stream()
                .map(GameCharacter::getName)
                .toList();
        return GameCharacterRegionResponseBO.builder()
                .region(region)
                .nameList(nameList)
                .build();
    }

    private Integer resolveNewOrder(List<GameCharacter> gameCharacterByRegionList,
            GameCharacterRequestBO gameCharacterRequestBO) {
        if (gameCharacterByRegionList.isEmpty()) {
            return 0;
        }

        GameCharacter refGameCharacter = gameCharacterByRegionList
                .stream()
                .filter(c -> c.getName().equals(gameCharacterRequestBO.getRefName()))
                .findFirst()
                .orElse(null);

        if (refGameCharacter == null) {
            System.out.println("not exist");
            return gameCharacterByRegionList.size();
        }

        if ("BEFORE".equals(gameCharacterRequestBO.getInsertPos()))
            return refGameCharacter.getOrder();

        if ("AFTER".equals(gameCharacterRequestBO.getInsertPos()))
            return refGameCharacter.getOrder() + 1;

        System.out.println("invalid insert position");
        return gameCharacterByRegionList.size();
    }

    private GameCharacter rebuildWithNewOrder(GameCharacter gameCharacter, Integer newOrder) {
        return GameCharacter.builder()
                .order(newOrder)
                .name(gameCharacter.getName())
                .sex(gameCharacter.getSex())
                .region(gameCharacter.getRegion())
                .quality(gameCharacter.getQuality())
                .build();
    }
}
