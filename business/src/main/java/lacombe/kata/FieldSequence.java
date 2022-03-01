package lacombe.kata;

import java.util.Set;

public record FieldSequence(Set<Cell> cellList) {

    public boolean isCompletelyTakenBy(Player player) {
        return cellList.stream()
                .map(Cell::getWasPlayedBy)
                .allMatch(player::equals);
    }
}
