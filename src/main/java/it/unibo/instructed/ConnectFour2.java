package it.unibo.instructed;

import java.util.ArrayList;
import java.util.List;

import it.unibo.ConnectFour;

public class ConnectFour2 implements ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private final List<List<Character>> board;

    public ConnectFour2() {
        board = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < COLS; j++) {
                row.add(' ');
            }
            board.add(row);
        }
    }

    @Override
    public void dropDisc(final int column, final char disc) {
        if (column < 0 || column >= COLS) {
            throw new IllegalArgumentException("Invalid column: " + column);
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board.get(row).get(column) == ' ') {
                board.get(row).set(column, disc);
                return;
            }
        }
        throw new IllegalStateException("Column " + column + " is full");
    }

    @Override
    public boolean checkWin(final Player player) {
        final char disc = (player == Player.RED) ? 'R' : 'Y';
        // Horizontal check
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board.get(row).get(col) == disc && board.get(row).get(col + 1) == disc &&
                    board.get(row).get(col + 2) == disc && board.get(row).get(col + 3) == disc) {
                    return true;
                }
            }
        }
        // Vertical check
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board.get(row).get(col) == disc && board.get(row + 1).get(col) == disc &&
                    board.get(row + 2).get(col) == disc && board.get(row + 3).get(col) == disc) {
                    return true;
                }
            }
        }
        // Diagonal check (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board.get(row).get(col) == disc && board.get(row + 1).get(col + 1) == disc &&
                    board.get(row + 2).get(col + 2) == disc && board.get(row + 3).get(col + 3) == disc) {
                    return true;
                }
            }
        }
        // Diagonal check (top-right to bottom-left)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 3; col < COLS; col++) {
                if (board.get(row).get(col) == disc && board.get(row + 1).get(col - 1) == disc &&
                    board.get(row + 2).get(col - 2) == disc && board.get(row + 3).get(col - 3) == disc) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board.get(0).get(col) == ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printBoard() {
        for (final List<Character> row : board) {
            for (final var cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }
}