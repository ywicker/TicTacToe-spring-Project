package lacombe.kata;

import java.util.stream.Stream;

import static lacombe.kata.Player.PLAYER_O;
import static lacombe.kata.Player.PLAYER_X;

public enum GameState {
    PLAYER_X_TURN(PLAYER_X) {
        public GameState defaultNextState(){
            return PLAYER_O_TURN;
        }
    },
    PLAYER_O_TURN(PLAYER_O) {
        public GameState defaultNextState(){
            return PLAYER_X_TURN;
        }
    },
    IS_OVER(null),
    PLAYER_X_WINS(PLAYER_X),
    PLAYER_O_WINS(PLAYER_O);

    private final Player player;

    GameState(Player player) {
        this.player = player;
    }

    public GameState defaultNextState(){
        return null;
    }
    public GameState gameWonBy(Player player){
        return Stream.of(PLAYER_X_WINS, PLAYER_O_WINS)
                .filter(state -> player.equals(state.player))
                .findAny().orElseThrow();
    }

    public Player getPlayer() {
        return player;
    }
}
