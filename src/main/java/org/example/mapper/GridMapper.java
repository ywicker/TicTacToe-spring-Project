package org.example.mapper;

import lacombe.kata.Cell;
import org.apache.logging.log4j.util.Strings;
import org.example.dto.GridDTO;

import java.util.Set;

public class GridMapper {

    PlayersShotMapper playersShotMapper;

    public GridMapper() {
        this.playersShotMapper = new PlayersShotMapper();
    }

    public GridDTO toGridDTO(Set<Cell> grid) {
        String[][] cells = {
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY}
        };
        grid.forEach(cell -> {
            int x = cell.getCoordinate().x() - 1;
            int y = cell.getCoordinate().y() - 1;
            cells[y][x] = playersShotMapper.toPlayerDTO(cell.getWasPlayedBy());
        });
        return new GridDTO(cells);
    }
}
