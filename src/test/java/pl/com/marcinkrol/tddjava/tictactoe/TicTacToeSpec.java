package pl.com.marcinkrol.tddjava.tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToe ticTacToe;

    @Before
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXIsOutOfBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenYIsOutOfBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }

    @Test
    public void whenInputIsOnOccupiedFieldThenRuntimeException() {
        ticTacToe.play(2, 2);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 2);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnThenNextPlayerThen0() {
        ticTacToe.play(1, 1);
        assertEquals('0', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertEquals("No Winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); //X
        ticTacToe.play(1, 2); //0
        ticTacToe.play(2, 1); //X
        ticTacToe.play(2, 2); //0
        String actual = ticTacToe.play(3, 1); //X
        assertEquals("The Winner is X", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 2);
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(1, 2);
        ticTacToe.play(3, 1);
        String actual = ticTacToe.play(1, 3);
        assertEquals("The Winner is 0", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        String actual = ticTacToe.play(3, 3);
        assertEquals("The Winner is X", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(3, 1);
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 2);
        ticTacToe.play(2, 1);
        String actual = ticTacToe.play(1, 3);
        assertEquals("The Winner is X", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }

}
