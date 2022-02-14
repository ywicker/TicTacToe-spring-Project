package org.example.mapper;

import lacombe.kata.GameState;
import org.example.dto.GameStateDTO;

public class GameStateMapper {
    public GameStateDTO toGameStateDTO(GameState gameState) {
        return GameStateDTO.valueOf(gameState.name());
    }
}
