package it.unibo;

public class ConnectFourGame implements ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private char[][] board; //I would have done a map of pairs, not a double pointer...

    public ConnectFourGame() {
        board = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void dropDisc(int column, char disc) {
        if (column < 0 || column >= COLS) {
            throw new IllegalArgumentException("Invalid column");
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == ' ') {
                board[row][column] = disc;
                return;
            }
        }
        throw new IllegalStateException("Column is full");
    }

    @Override
    public boolean checkWin(Player player) {
        char disc = (player == Player.RED) ? 'R' : 'Y';
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == disc && board[row][col+1] == disc && board[row][col+2] == disc && board[row][col+3] == disc) {
                    return true;
                }
            }
        }
        // Check vertical
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board[row][col] == disc && board[row+1][col] == disc && board[row+2][col] == disc && board[row+3][col] == disc) {
                    return true;
                }
            }
        }
        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == disc && board[row+1][col+1] == disc && board[row+2][col+2] == disc && board[row+3][col+3] == disc) {
                    return true;
                }
            }
        }
        // Check diagonal (top-right to bottom-left)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 3; col < COLS; col++) {
                if (board[row][col] == disc && board[row+1][col-1] == disc && board[row+2][col-2] == disc && board[row+3][col-3] == disc) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }
}