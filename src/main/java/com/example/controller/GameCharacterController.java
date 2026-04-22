package com.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.GameCharacterRegionResponseDTO;
import com.example.dto.GameCharacterRequestDTO;
import com.example.dto.GameCharacterResponseDTO;
import com.example.service.GameCharacterService;
import com.example.service.bo.GameCharacterRegionResponseBO;
import com.example.service.bo.GameCharacterRequestBO;
import com.example.service.bo.GameCharacterResponseBO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/game/{gameName}/character")
@RequiredArgsConstructor
public class GameCharacterController {

    private final GameCharacterService gameCharacterService;

    @GetMapping
    public ResponseEntity<GameCharacterResponseDTO> getGameCharacter(@RequestHeader("X-User-Name") String username,
            @PathVariable String gameName) {
        GameCharacterResponseBO responseBO = gameCharacterService.getSpecificGameCharacter(username, gameName);
        return ResponseEntity.ok(toResponseDTO(responseBO));
    }

    @PostMapping
    public ResponseEntity<Void> saveGameCharacter(@RequestHeader("X-User-Name") String username,
            @PathVariable String gameName,
            @RequestBody GameCharacterRequestDTO gameCharacterRequestDTO) {
        gameCharacterService.saveGameCharacter(username, gameName, toRequestBO(gameCharacterRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private GameCharacterRequestBO toRequestBO(GameCharacterRequestDTO gameCharacterRequestDTO) {
        return GameCharacterRequestBO.builder()
                .order(gameCharacterRequestDTO.getOrder()).name(gameCharacterRequestDTO.getName())
                .sex(gameCharacterRequestDTO.getSex()).region(gameCharacterRequestDTO.getRegion())
                .quality(gameCharacterRequestDTO.getQuality()).build();
    }

    private GameCharacterResponseDTO toResponseDTO(GameCharacterResponseBO gameCharacterResponseBO) {
        return GameCharacterResponseDTO.builder().gameName(gameCharacterResponseBO.getGameName())
                .gameCharacterRegionResponseDTOList(gameCharacterResponseBO.getGameCharacterRegionResponseBOList()
                        .stream().map(this::toRegionResponseDTO).toList())
                .build();
    }

    private GameCharacterRegionResponseDTO toRegionResponseDTO(
            GameCharacterRegionResponseBO gameCharacterRegionResponseBO) {
        return GameCharacterRegionResponseDTO.builder().region(gameCharacterRegionResponseBO.getRegion())
                .nameList(gameCharacterRegionResponseBO.getNameList()).build();
    }
}
