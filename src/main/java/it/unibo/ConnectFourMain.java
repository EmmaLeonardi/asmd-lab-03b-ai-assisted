package it.unibo;

import java.util.InputMismatchException;
import java.util.Scanner;

import it.unibo.ConnectFour.Player;

public class ConnectFourMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = Player.RED;
        ConnectFour game = new ConnectFourGame();

        while (!game.isBoardFull() && !game.checkWin(Player.RED) && !game.checkWin(Player.YELLOW)) {
            game.printBoard();
            System.out.println("Player " + currentPlayer + ", enter column (0-6): ");
            int column = -1;
            boolean validInput = false;
            while (!validInput) {
                try {
                    column = scanner.nextInt();
                    if (column < 0 || column > 6) {
                        System.out.println("Column must be between 0 and 6. Try again: ");
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid integer. Try again: ");
                    scanner.nextLine(); // consume the invalid input
                }
            }
            try {
                char disc = (currentPlayer == Player.RED) ? 'R' : 'Y';
                game.dropDisc(column, disc);
                currentPlayer = (currentPlayer == Player.RED) ? Player.YELLOW : Player.RED;
            } catch (Exception e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }
        game.printBoard();
        scanner.close();

    }
}