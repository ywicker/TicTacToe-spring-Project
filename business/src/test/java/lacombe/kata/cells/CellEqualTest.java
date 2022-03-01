package lacombe.kata.cells;

import lacombe.kata.Cell;
import lacombe.kata.Coordinate;
import org.junit.jupiter.api.Test;

import static lacombe.kata.Player.PLAYER_X;
import static org.assertj.core.api.Assertions.assertThat;

public class CellEqualTest {
    @Test
    void cell_with_same_x_but_different_y_are_not_equals() {
        var cell_1_1 = new Cell(new Coordinate(1, 1));

        var cell_1_2 = new Cell(new Coordinate(1, 2));
        assertThat(cell_1_1).isNotEqualTo(cell_1_2);
    }
    @Test
    void cell_with_different_x_but_same_y_are_not_equals() {
        var cell_1_1 = new Cell(new Coordinate(1, 1));

        var cell_2_1 = new Cell(new Coordinate(2, 1));
        assertThat(cell_1_1).isNotEqualTo(cell_2_1);
    }
    @Test
    void filled_cell_with_same_x_and_y_as_another_empty_cell_are_equals() throws Exception {
        var filled_cell_1_1 = new Cell(new Coordinate(1, 1));
        filled_cell_1_1.setWasPlayedBy(PLAYER_X);

        var cell_1_1 = new Cell(new Coordinate(1, 1));
        assertThat(filled_cell_1_1).isEqualTo(cell_1_1);
    }
}
