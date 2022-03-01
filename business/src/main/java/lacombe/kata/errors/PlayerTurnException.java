package lacombe.kata.errors;

import lacombe.kata.Player;

public class PlayerTurnException extends Exception {
    public PlayerTurnException(Player player) {
        super("The player " + player.name() + " cant play");
    }
}
