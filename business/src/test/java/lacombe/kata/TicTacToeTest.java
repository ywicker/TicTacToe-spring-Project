package lacombe.kata;

import lacombe.kata.errors.GameStateIsOverException;
import lacombe.kata.errors.PlayerTurnException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static lacombe.kata.GameState.*;
import static lacombe.kata.Player.PLAYER_O;
import static lacombe.kata.Player.PLAYER_X;
import static org.assertj.core.api.Assertions.assertThat;

public class TicTacToeTest {
    @Test
    void player_X_is_playing_should_change_state_to_player_0_turn() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);

        assertThat(ticTacToe.state()).isEqualTo(PLAYER_O_TURN);
    }
    @Test
    void player_0_is_playing_after_player_X_should_change_state_to_player_X_turn() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);
        ticTacToe.play(PLAYER_O, 1, 2);

        assertThat(ticTacToe.state()).isEqualTo(PLAYER_X_TURN);
    }
    @Test
    void player_O_plays_first_should_return_error() {
        var ticTacToe = new TicTacToe();

        Assertions.assertThatThrownBy(() -> ticTacToe.play(PLAYER_O, 1, 1))
                .isInstanceOf(PlayerTurnException.class);
    }
    @Test
    void player_X_is_playing_twice_should_return_error() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);

        Assertions.assertThatThrownBy(() -> ticTacToe.play(PLAYER_X, 1, 2))
                .isInstanceOf(PlayerTurnException.class);
    }
    @Test
    void is_over_when_all_fields_are_taken() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);
        ticTacToe.play(PLAYER_O, 2, 2);
        ticTacToe.play(PLAYER_X, 1, 2);
        ticTacToe.play(PLAYER_O, 1, 3);
        ticTacToe.play(PLAYER_X, 3, 1);
        ticTacToe.play(PLAYER_O, 2, 1);
        ticTacToe.play(PLAYER_X, 2, 3);
        ticTacToe.play(PLAYER_O, 3, 2);
        ticTacToe.play(PLAYER_X, 3, 3);

        assertThat(ticTacToe.state()).isEqualTo(IS_OVER);
    }
    @Test
    void is_over_when_all_fields_in_a_column_are_taken() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 3, 1);
        ticTacToe.play(PLAYER_O, 2, 2);
        ticTacToe.play(PLAYER_X, 3, 3);
        ticTacToe.play(PLAYER_O, 2, 3);
        ticTacToe.play(PLAYER_X, 3, 2);

        assertThat(ticTacToe.state()).isEqualTo(PLAYER_X_WINS);
    }
    @Test
    void player_is_playing_when_is_over_should_return_error() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);
        ticTacToe.play(PLAYER_O, 3, 1);
        ticTacToe.play(PLAYER_X, 2, 2);
        ticTacToe.play(PLAYER_O, 3, 3);
        ticTacToe.play(PLAYER_X, 2, 3);
        ticTacToe.play(PLAYER_O, 3, 2);

        Assertions.assertThatThrownBy(() -> ticTacToe.play(PLAYER_X, 1, 2))
                .isInstanceOf(GameStateIsOverException.class);
    }
    @Test
    void is_over_when_all_fields_in_a_diagonal_are_taken() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);
        ticTacToe.play(PLAYER_O, 1, 3);
        ticTacToe.play(PLAYER_X, 1, 2);
        ticTacToe.play(PLAYER_O, 2, 2);
        ticTacToe.play(PLAYER_X, 3, 3);
        ticTacToe.play(PLAYER_O, 3, 1);

        assertThat(ticTacToe.state()).isEqualTo(PLAYER_O_WINS);
    }
    @Test
    void is_over_when_all_fields_are_taken_and_one_player_wins() throws Exception {
        var ticTacToe = new TicTacToe();

        ticTacToe.play(PLAYER_X, 1, 1);
        ticTacToe.play(PLAYER_O, 2, 2);
        ticTacToe.play(PLAYER_X, 1, 2);
        ticTacToe.play(PLAYER_O, 1, 3);
        ticTacToe.play(PLAYER_X, 3, 1);
        ticTacToe.play(PLAYER_O, 3, 2);
        ticTacToe.play(PLAYER_X, 3, 3);
        ticTacToe.play(PLAYER_O, 2, 3);
        ticTacToe.play(PLAYER_X, 2, 1);

        assertThat(ticTacToe.state()).isEqualTo(PLAYER_X_WINS);
    }
}
