package org.example.controller;

import lacombe.kata.Coordinate;
import lacombe.kata.Player;
import org.example.dto.GameStateDTO;
import org.example.dto.GridDTO;
import org.example.dto.PlayersShotDTO;
import org.example.mapper.GameStateMapper;
import org.example.mapper.GridMapper;
import org.example.mapper.PlayersShotMapper;
import org.example.service.TicTacToeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicTacToeController {

    private final TicTacToeService ticTacToeService;
    private final PlayersShotMapper playersShotMapper;
    private final GameStateMapper gameStateDTOMapper;
    private final GridMapper gridMapper;

    TicTacToeController(TicTacToeService ticTacToeService){
        this.ticTacToeService = ticTacToeService;
        this.playersShotMapper = new PlayersShotMapper();
        this.gameStateDTOMapper = new GameStateMapper();
        this.gridMapper = new GridMapper();
    }

    @PostMapping("/TicTacToe/play")
    @ResponseStatus(HttpStatus.OK)
    public void play(@RequestBody PlayersShotDTO playersShotDTO) {
        Player player = playersShotMapper.toPlayer(playersShotDTO.playerValue());
        Coordinate coordinate = playersShotMapper.toCoordinate(playersShotDTO.coordinateDTO());
        try {
            ticTacToeService.play(player, coordinate);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
    @GetMapping(path = "/TicTacToe/state", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameStateDTO> state() {
        GameStateDTO gameStateDTO = gameStateDTOMapper.toGameStateDTO(ticTacToeService.state());
        return ResponseEntity.ok(gameStateDTO);
    }
    @GetMapping(path = "/TicTacToe/grid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GridDTO> getGrid() {
        GridDTO gridDTO = gridMapper.toGridDTO(ticTacToeService.getGrid());
        return ResponseEntity.ok(gridDTO);
    }

    @PostMapping("/TicTacToe/reset")
    @ResponseStatus(HttpStatus.OK)
    public void reset() {
        ticTacToeService.resetGame();
    }



    @GetMapping(path = "/TicTacToe/showStateGrid2", produces = MediaType.ALL_VALUE)
    public String showStatGrid() {
        GridDTO gridDTO = gridMapper.toGridDTO(ticTacToeService.getGrid());
        String[][] cells = gridDTO.cells();
        String statGrid = "<table>";
        for(int y = 0; y <3; y++){
            statGrid+="<tr>";
            for(int x = 0; x <3; x++) {
                statGrid+="<td>";
                String player = cells[y][x];
                if(player.isEmpty()){
                    statGrid+="_";
                } else if (player == "PLAYER_X") {
                    statGrid+="X";
                } else if (player == "PLAYER_O") {
                    statGrid+="O";
                }
                statGrid+="</td>";
            }
            statGrid+="</tr>";
        }
        statGrid+="</table>";
        return statGrid;
    }
}
