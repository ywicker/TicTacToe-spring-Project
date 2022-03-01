package lacombe.kata.errors;

import lacombe.kata.Coordinate;

public class CellNotExistException extends Exception {
    public CellNotExistException(Coordinate coordinate) {
        super("the cell with "+coordinate+" does not exist");
    }
}
