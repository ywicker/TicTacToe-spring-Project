package lacombe.kata.errors;

public class AlreadyPlayedException extends Exception {
    public AlreadyPlayedException() {
        super("This cell is already played by a player");
    }
}
