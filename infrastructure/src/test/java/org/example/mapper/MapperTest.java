package org.example.mapper;

import lacombe.kata.Cell;
import lacombe.kata.Coordinate;
import lacombe.kata.GameState;
import lacombe.kata.Player;
import lacombe.kata.errors.AlreadyPlayedException;
import org.apache.logging.log4j.util.Strings;
import org.example.dto.CoordinateDTO;
import org.example.dto.GameStateDTO;
import org.example.dto.GridDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {
    PlayersShotMapper playersShotMapper;
    GameStateMapper gameStateDTOMapper;
    GridMapper gridMapper;

    @BeforeEach
    public void setup(){
        playersShotMapper = new PlayersShotMapper();
        gameStateDTOMapper = new GameStateMapper();
        gridMapper = new GridMapper();
    }

    @Test
    public void map_coordinate_DTO_to_coordinate_1_1(){
        CoordinateDTO coordinateDTO = new CoordinateDTO(1,1);

        Coordinate coordinate = playersShotMapper.toCoordinate(coordinateDTO);

        Coordinate expectedCoordinate = new Coordinate(1,1);
        assertThat(coordinate).isEqualTo(expectedCoordinate);
    }
    @Test
    public void map_coordinate_DTO_to_coordinate_2_2(){
        CoordinateDTO coordinateDTO = new CoordinateDTO(2,2);

        Coordinate coordinate = playersShotMapper.toCoordinate(coordinateDTO);

        Coordinate expectedCoordinate = new Coordinate(2,2);
        assertThat(coordinate).isEqualTo(expectedCoordinate);
    }
    @Test
    public void map_player_DTO_X_value_to_player(){
        String playerValue = "PLAYER_X";

        Player player = playersShotMapper.toPlayer(playerValue);

        assertThat(player).isEqualTo(Player.PLAYER_X);
    }
    @Test
    public void map_player_DTO_O_value_to_player(){
        String playerValue = "PLAYER_O";

        Player player = playersShotMapper.toPlayer(playerValue);

        assertThat(player).isEqualTo(Player.PLAYER_O);
    }
    @Test
    public void map_player_X_value_to_player_DTO(){
        String playerDTO = playersShotMapper.toPlayerDTO(Player.PLAYER_X);

        assertThat(playerDTO).isEqualTo("PLAYER_X");
    }
    @Test
    public void map_player_O_value_to_player_DTO(){
        String playerDTO = playersShotMapper.toPlayerDTO(Player.PLAYER_O);

        assertThat(playerDTO).isEqualTo("PLAYER_O");
    }
    @Test
    public void map_player_null_value_to_player_DTO(){
        String playerDTO = playersShotMapper.toPlayerDTO(null);

        assertThat(playerDTO).isEqualTo(Strings.EMPTY);
    }
    @Test
    public void map_game_state_PLAYER_O_TURN_to_game_state_DTO(){
        GameStateDTO gameStateDTO = gameStateDTOMapper.toGameStateDTO(GameState.PLAYER_O_TURN);

        assertThat(gameStateDTO).isEqualTo(GameStateDTO.PLAYER_O_TURN);
    }
    @Test
    public void map_game_state_PLAYER_X_TURN_to_game_state_DTO(){
        GameStateDTO gameStateDTO = gameStateDTOMapper.toGameStateDTO(GameState.PLAYER_X_TURN);

        assertThat(gameStateDTO).isEqualTo(GameStateDTO.PLAYER_X_TURN);
    }
    @Test
    public void map_game_state_IS_OVER_to_game_state_DTO(){
        GameStateDTO gameStateDTO = gameStateDTOMapper.toGameStateDTO(GameState.IS_OVER);

        assertThat(gameStateDTO).isEqualTo(GameStateDTO.IS_OVER);
    }
    @Test
    public void map_game_state_PLAYER_O_WINS_to_game_state_DTO(){
        GameStateDTO gameStateDTO = gameStateDTOMapper.toGameStateDTO(GameState.PLAYER_O_WINS);

        assertThat(gameStateDTO).isEqualTo(GameStateDTO.PLAYER_O_WINS);
    }
    @Test
    public void map_game_state_PLAYER_X_WINS_to_game_state_DTO(){
        GameStateDTO gameStateDTO = gameStateDTOMapper.toGameStateDTO(GameState.PLAYER_X_WINS);

        assertThat(gameStateDTO).isEqualTo(GameStateDTO.PLAYER_X_WINS);
    }
    @Test
    public void map_empty_grid_to_grid_DTO(){
        Set<Cell> cells = Set.of();

        GridDTO gridDTO = gridMapper.toGridDTO(cells);

        String[][] expectedCells = {
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY}
        };
        GridDTO expectedGridDTO = new GridDTO(expectedCells);
        assertThat(gridDTO).isEqualTo(expectedGridDTO);
    }
    @Test
    public void map_grid_with_1_empty_cell_to_grid_DTO(){
        Set<Cell> cells = Set.of(new Cell(new Coordinate(1, 1)));

        GridDTO gridDTO = gridMapper.toGridDTO(cells);

        String[][] expectedCells = {
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY}
        };
        GridDTO expectedGridDTO = new GridDTO(expectedCells);
        assertThat(gridDTO).isEqualTo(expectedGridDTO);
    }
    @Test
    public void map_grid_with_1_filled_cell_to_grid_DTO() throws AlreadyPlayedException {
        Cell cell = new Cell(new Coordinate(2, 1));
        cell.setWasPlayedBy(Player.PLAYER_X);
        Set<Cell> cells = Set.of(cell);

        GridDTO gridDTO = gridMapper.toGridDTO(cells);

        String[][] expectedCells = {
                {Strings.EMPTY, "PLAYER_X", Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY}
        };
        GridDTO expectedGridDTO = new GridDTO(expectedCells);
        assertThat(gridDTO).isEqualTo(expectedGridDTO);
    }
    @Test
    public void map_grid_with_2_filled_cells_to_grid_DTO() throws AlreadyPlayedException {
        Cell cell_X = new Cell(new Coordinate(2, 2));
        Cell cell_O = new Cell(new Coordinate(1, 3));
        cell_X.setWasPlayedBy(Player.PLAYER_X);
        cell_O.setWasPlayedBy(Player.PLAYER_O);
        Set<Cell> cells = Set.of(cell_X, cell_O);

        GridDTO gridDTO = gridMapper.toGridDTO(cells);

        String[][] expectedCells = {
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, "PLAYER_X", Strings.EMPTY},
                {"PLAYER_O", Strings.EMPTY, Strings.EMPTY}
        };
        GridDTO expectedGridDTO = new GridDTO(expectedCells);
        assertThat(gridDTO).isEqualTo(expectedGridDTO);
    }
}
