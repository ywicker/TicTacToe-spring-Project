package org.example.dto;

public enum GameStateDTO {
    PLAYER_X_TURN("PLAYER_X"),
    PLAYER_O_TURN("PLAYER_O"),
    IS_OVER(null),
    PLAYER_X_WINS("PLAYER_X"),
    PLAYER_O_WINS("PLAYER_O");

    private final String playerValue;

    GameStateDTO(String playerValue) {
        this.playerValue = playerValue;
    }

}
