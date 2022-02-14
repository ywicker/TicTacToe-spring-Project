package org.example.mapper;

import lacombe.kata.Coordinate;
import lacombe.kata.Player;
import org.apache.logging.log4j.util.Strings;
import org.example.dto.CoordinateDTO;

public class PlayersShotMapper {
    public Player toPlayer(String playerValue) {
        return Player.valueOf(playerValue);
    }
    public String toPlayerDTO(Player playerValue) {
        if(playerValue == null) {
            return Strings.EMPTY;
        }
        return playerValue.name();
    }

    public Coordinate toCoordinate(CoordinateDTO coordinateDTO) {
        return new Coordinate(coordinateDTO.x(), coordinateDTO.y());
    }
}
