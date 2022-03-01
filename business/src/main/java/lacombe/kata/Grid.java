package lacombe.kata;

import lacombe.kata.errors.CellNotExistException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {
    private final Set<Cell> cellList;
    private static final LinkedList<Integer> GRID_RANGE;
    static {
        GRID_RANGE = IntStream.rangeClosed(1, 3)
                .boxed().collect(Collectors.toCollection(LinkedList::new));
    }

    public Grid() {
        var coordinates = Coordinate.coordinateListOfSizeGrid(GRID_RANGE);
        cellList = coordinates.stream().map(Cell::new).collect(Collectors.toSet());
    }

    public void set(Player player, Coordinate coordinate) throws Exception {
        var cell = this.get(coordinate);

        if(cell==null){
            throw new CellNotExistException(coordinate);
        }

        cell.setWasPlayedBy(player);
    }
    public Set<Cell> getCells() {
        return Set.copyOf(cellList);
    }
    public Cell get(Coordinate coordinate) {
        return cellList.stream()
                .filter(cell -> coordinate.equals(cell.getCoordinate()))
                .findAny()
                .orElse(null);
    }

    public boolean allFieldsAreTaken() {
        return cellList.stream()
                .map(Cell::getWasPlayedBy)
                .noneMatch(Objects::isNull);
    }

    public List<FieldSequence> rows() {
        return GRID_RANGE.stream().map(y ->
                new FieldSequence(cellList.stream()
                        .filter(cell -> cell.getCoordinate().isOnRowOf(y))
                        .collect(Collectors.toSet()))
        ).toList();
    }
    public List<FieldSequence> columns() {
        return GRID_RANGE.stream().map(x ->
                new FieldSequence(cellList.stream()
                        .filter(cell -> cell.getCoordinate().isOnColumnOf(x))
                        .collect(Collectors.toSet()))
        ).toList();
    }
    public List<FieldSequence> diagonals() {
        return List.of(
                new FieldSequence(cellList.stream()
                    .filter(cell -> cell.getCoordinate().isOnDiagonal())
                        .collect(Collectors.toSet())),
                new FieldSequence(cellList.stream()
                    .filter(cell -> (cell.getCoordinate().isOnOppositeDiagonal(GRID_RANGE)))
                        .collect(Collectors.toSet()))
        );
    }
}
