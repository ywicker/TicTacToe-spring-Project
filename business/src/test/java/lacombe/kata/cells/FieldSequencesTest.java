package lacombe.kata.cells;

import lacombe.kata.Coordinate;
import lacombe.kata.FieldSequences;
import lacombe.kata.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static lacombe.kata.Player.PLAYER_O;
import static lacombe.kata.Player.PLAYER_X;

public class FieldSequencesTest {
    @Test
    void all_fields_in_a_line_are_taken_by_the_player_X_when_the_first_line_has_been_filled_by_player_X() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 1));
        cells.set(PLAYER_O, new Coordinate(2, 2));
        cells.set(PLAYER_X, new Coordinate(2, 1));
        cells.set(PLAYER_O, new Coordinate(3, 2));
        cells.set(PLAYER_X, new Coordinate(3, 1));

        Assertions.assertTrue(fieldSequences.anyIsCompletelyTokenBy(PLAYER_X));
    }
    @Test
    void all_fields_in_a_line_are_taken_by_the_player_O_when_the_third_line_has_been_filled_by_player_O() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 1));
        cells.set(PLAYER_O, new Coordinate(1, 3));
        cells.set(PLAYER_X, new Coordinate(2, 2));
        cells.set(PLAYER_O, new Coordinate(3, 3));
        cells.set(PLAYER_X, new Coordinate(3, 1));
        cells.set(PLAYER_O, new Coordinate(2, 3));

        Assertions.assertTrue(fieldSequences.anyIsCompletelyTokenBy(PLAYER_O));
    }
    @Test
    void all_fields_in_a_column_are_taken_by_the_player_X_when_the_first_column_has_been_filled_by_player_X() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 1));
        cells.set(PLAYER_O, new Coordinate(2, 2));
        cells.set(PLAYER_X, new Coordinate(1, 2));
        cells.set(PLAYER_O, new Coordinate(2, 3));
        cells.set(PLAYER_X, new Coordinate(1, 3));

        Assertions.assertTrue(fieldSequences.anyIsCompletelyTokenBy(PLAYER_X));
    }
    @Test
    void all_fields_in_a_column_are_taken_by_the_player_O_when_the_third_column_has_been_filled_by_player_O() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 1));
        cells.set(PLAYER_O, new Coordinate(3, 1));
        cells.set(PLAYER_X, new Coordinate(2, 2));
        cells.set(PLAYER_O, new Coordinate(3, 3));
        cells.set(PLAYER_X, new Coordinate(2, 3));
        cells.set(PLAYER_O, new Coordinate(3, 2));

        Assertions.assertTrue(fieldSequences.anyIsCompletelyTokenBy(PLAYER_O));
    }
    @Test
    void all_fields_in_a_diagonal_are_taken_by_the_player_X_when_the_first_diagonal_has_been_filled_by_player_X() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 1));
        cells.set(PLAYER_O, new Coordinate(1, 2));
        cells.set(PLAYER_X, new Coordinate(2, 2));
        cells.set(PLAYER_O, new Coordinate(1, 3));
        cells.set(PLAYER_X, new Coordinate(3, 3));

        Assertions.assertTrue(fieldSequences.anyIsCompletelyTokenBy(PLAYER_X));
    }
    @Test
    void all_fields_in_a_diagonal_are_not_taken_by_the_player_X_when_the_first_diagonal_has_not_been_filled_by_player_X() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 1));
        cells.set(PLAYER_O, new Coordinate(1, 2));
        cells.set(PLAYER_X, new Coordinate(2, 2));
        cells.set(PLAYER_O, new Coordinate(3, 3));
        cells.set(PLAYER_X, new Coordinate(1, 3));

        Assertions.assertFalse(fieldSequences.anyIsCompletelyTokenBy(PLAYER_X));
    }
    @Test
    void all_fields_in_a_diagonal_are_taken_by_the_player_X_when_the_second_diagonal_has_been_filled_by_player_X() throws Exception {
        var cells = new Grid();
        var fieldSequences = new FieldSequences(cells);

        cells.set(PLAYER_X, new Coordinate(1, 3));
        cells.set(PLAYER_O, new Coordinate(1, 2));
        cells.set(PLAYER_X, new Coordinate(2, 2));
        cells.set(PLAYER_O, new Coordinate(3, 3));
        cells.set(PLAYER_X, new Coordinate(3, 1));

        Assertions.assertTrue(fieldSequences.anyIsCompletelyTokenBy(PLAYER_X));
    }
}
