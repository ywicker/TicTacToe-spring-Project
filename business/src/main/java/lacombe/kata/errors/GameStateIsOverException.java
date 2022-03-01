package lacombe.kata.errors;

public class GameStateIsOverException extends Exception {
    public GameStateIsOverException() {
       super("the is over");
    }
}
