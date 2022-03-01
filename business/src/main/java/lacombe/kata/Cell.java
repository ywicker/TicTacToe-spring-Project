package lacombe.kata;

import lacombe.kata.errors.AlreadyPlayedException;

import java.util.Objects;

public class Cell {
    private final Coordinate coordinate;
    private Player player;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Player getWasPlayedBy() {
        return player;
    }

    public void setWasPlayedBy(Player player) throws AlreadyPlayedException {
        if(this.player != null) {
            throw new AlreadyPlayedException();
        }
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return coordinate.equals(cell.getCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinate=" + coordinate +
                ", player=" + player +
                '}';
    }
}
