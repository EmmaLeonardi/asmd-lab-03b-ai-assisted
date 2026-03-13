package it.unibo;

import org.junit.jupiter.api.Test;

import it.unibo.instructed.ConnectFour2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConnectFour2Test {
    @Test
    public void testDropDiscValid() {
        final var game = new ConnectFour2();
        game.dropDisc(0, 'R');
        // Assuming we can check the board, but since no getter, perhaps test by checking win or something
        // For now, just ensure no exception
        assertDoesNotThrow(() -> game.dropDisc(0, 'Y'));
    }

    @Test
    public void testDropDiscInvalidColumn() {
        final var game = new ConnectFour2();
        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(-1, 'R'));
        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(7, 'R'));
    }

    @Test
    public void testDropDiscFullColumn() {
        final var game = new ConnectFour2();
        for (int i = 0; i < 6; i++) {
            game.dropDisc(0, 'R');
        }
        assertThrows(IllegalStateException.class, () -> game.dropDisc(0, 'Y'));
    }

    @Test
    public void testCheckWinHorizontal() {
        final var game = new ConnectFour2();
        for (int col = 0; col < 4; col++) {
            game.dropDisc(col, 'R');
        }
        assertTrue(game.checkWin(ConnectFour.Player.RED));
        assertFalse(game.checkWin(ConnectFour.Player.YELLOW));
    }

    @Test
    public void testCheckWinVertical() {
        final var game = new ConnectFour2();
        for (int i = 0; i < 4; i++) {
            game.dropDisc(0, 'R');
        }
        assertTrue(game.checkWin(ConnectFour.Player.RED));
        assertFalse(game.checkWin(ConnectFour.Player.YELLOW));
    }

    @Test
    public void testCheckWinDiagonalTopLeftBottomRight() {
        final var game = new ConnectFour2();
        // Place discs to form diagonal win
        game.dropDisc(0, 'Y'); // bottom
        game.dropDisc(1, 'R');
        game.dropDisc(1, 'Y');
        game.dropDisc(2, 'R');
        game.dropDisc(2, 'R');
        game.dropDisc(2, 'Y');
        game.dropDisc(3, 'R');
        game.dropDisc(3, 'R');
        game.dropDisc(3, 'R');
        game.dropDisc(3, 'Y'); // top
        assertTrue(game.checkWin(ConnectFour.Player.YELLOW));
        assertFalse(game.checkWin(ConnectFour.Player.RED));
    }

    @Test
    public void testCheckWinDiagonalTopRightBottomLeft() {
        final var game = new ConnectFour2();
        // Place discs for other diagonal
        game.dropDisc(3, 'Y');
        game.dropDisc(2, 'R');
        game.dropDisc(2, 'Y');
        game.dropDisc(1, 'R');
        game.dropDisc(1, 'R');
        game.dropDisc(1, 'Y');
        game.dropDisc(0, 'R');
        game.dropDisc(0, 'R');
        game.dropDisc(0, 'R');
        game.dropDisc(0, 'Y');
        assertTrue(game.checkWin(ConnectFour.Player.YELLOW));
        assertFalse(game.checkWin(ConnectFour.Player.RED));
    }

    @Test
    public void testCheckWinNoWin() {
        final var game = new ConnectFour2();
        game.dropDisc(0, 'R');
        game.dropDisc(1, 'Y');
        assertFalse(game.checkWin(ConnectFour.Player.RED));
        assertFalse(game.checkWin(ConnectFour.Player.YELLOW));
    }

    @Test
    public void testIsBoardFullFalse() {
        final var game = new ConnectFour2();
        assertFalse(game.isBoardFull());
    }

    @Test
    public void testIsBoardFullTrue() {
        final var game = new ConnectFour2();
        // Fill the board
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                game.dropDisc(col, (col % 2 == 0) ? 'R' : 'Y');
            }
        }
        assertTrue(game.isBoardFull());
    }

    @Test
    public void testPrintBoard() {
        final var game = new ConnectFour2();
        game.dropDisc(0, 'R');
        // Capture output
        final var outputStream = new ByteArrayOutputStream();
        final var printStream = new PrintStream(outputStream);
        final var originalOut = System.out;
        System.setOut(printStream);
        try {
            game.printBoard();
            final var output = outputStream.toString();
            assertTrue(output.contains("R"));
            assertTrue(output.contains("0 1 2 3 4 5 6"));
        } finally {
            System.setOut(originalOut);
        }
    }
}