package lacombe.kata;

import java.util.ArrayList;
import java.util.List;

public class FieldSequences {
    private final List<FieldSequence> fieldSequenceList;

    public FieldSequences(Grid grid) {
       this.fieldSequenceList = new ArrayList<>();
        fieldSequenceList.addAll(grid.rows());
        fieldSequenceList.addAll(grid.columns());
        fieldSequenceList.addAll(grid.diagonals());
    }

    public boolean anyIsCompletelyTokenBy(Player player) {
        return fieldSequenceList.stream().anyMatch(sequenceOfField ->
                sequenceOfField.isCompletelyTakenBy(player)
        );
    }
}
