package org.example.service;

import lacombe.kata.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class TicTacToeService {
    private TicTacToe ticTacToe;

    @PostConstruct
    public void initialize() {
        ticTacToe = new TicTacToe();
    }

    public void play(Player player, Coordinate coordinate) throws Exception {
        ticTacToe.play(player, coordinate.x(), coordinate.y());
    }
    public GameState state(){
        return ticTacToe.state();
    }
    public Set<Cell> getGrid(){
        return ticTacToe.getCells();
    }

    public void resetGame() {
        ticTacToe = new TicTacToe();
    }
}
