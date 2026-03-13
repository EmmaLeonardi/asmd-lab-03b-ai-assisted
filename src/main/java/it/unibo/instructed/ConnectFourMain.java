package it.unibo.instructed;

import java.util.InputMismatchException;
import java.util.Scanner;

import it.unibo.ConnectFour.Player;

public class ConnectFourMain {
    public static void main(final String[] args) {
        final var game = new ConnectFourImpl();
        var currentPlayer = Player.RED;

        try (final var scanner = new Scanner(System.in)) {
            while (!game.isBoardFull() && !game.checkWin(Player.RED) && !game.checkWin(Player.YELLOW)) {
                game.printBoard();
                System.out.println("Player " + currentPlayer + ", enter column (0-6): ");
                final var column = getValidColumn(scanner);
                try {
                    final char disc = (currentPlayer == Player.RED) ? 'R' : 'Y';
                    game.dropDisc(column, disc);
                    currentPlayer = (currentPlayer == Player.RED) ? Player.YELLOW : Player.RED;
                } catch (final Exception e) {
                    System.out.println("Invalid move: " + e.getMessage());
                }
            }

            game.printBoard();
            if (game.checkWin(Player.RED)) {
                System.out.println("RED wins!");
            } else if (game.checkWin(Player.YELLOW)) {
                System.out.println("YELLOW wins!");
            } else {
                System.out.println("It's a draw!");
            }
        } catch (final Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static int getValidColumn(final Scanner scanner) {
        while (true) {
            try {
                final var column = scanner.nextInt();
                if (column < 0 || column > 6) {
                    System.out.println("Column must be between 0 and 6. Try again: ");
                } else {
                    return column;
                }
            } catch (final InputMismatchException e) {
                System.out.println("Please enter a valid integer. Try again: ");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
}