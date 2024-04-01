package org.example;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char player1 = 'X';
        char player2 = 'O';

        boolean playAgain = true;
        while (playAgain) {
            char[][] gameBoard = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    gameBoard[i][j] = ' ';
                }
            }
            boolean gameOngoing = true;
            boolean player1Turn = true;

            while (gameOngoing) {
                printGameBoard(gameBoard);
                System.out.println("Player " + (player1Turn ? player1 : player2) + ", enter your move (row[1-3] and column[1-3]): ");
                String rowInput = scanner.next();
                String colInput = scanner.next();

                if (!isNumeric(rowInput) || !isNumeric(colInput)) {
                    System.out.println("Invalid input, please enter number within the range [1-3].");
                    continue;
                }

                int row = Integer.parseInt(rowInput) - 1;
                int col = Integer.parseInt(colInput) - 1;

                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    System.out.println("Invalid input, please enter number within the range [1-3].");
                    continue;
                }

                if (gameBoard[row][col] == ' ') {
                    gameBoard[row][col] = player1Turn ? player1 : player2;
                    if (checkWin(gameBoard)) {
                        gameOngoing = false;
                        System.out.println("Game Over. Player " + (player1Turn ? player1 : player2) + " wins");
                    } else if (isDraw(gameBoard)) {
                        gameOngoing = false;
                        System.out.println("Game over. Game board is a draw.");
                    } else {
                        player1Turn = !player1Turn;
                    }
                } else {
                    System.out.println("Invalid move, cell is already occupied. Please, try again.");
                }
            }
            System.out.println("Do you want to play again? (yes/no)");
            String playAgainAnswer = scanner.next();
            playAgain = playAgainAnswer.equalsIgnoreCase("yes");
        }
        scanner.close();
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    public static boolean checkWin(char[][] gameBoard) {
//        checking rows
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] && gameBoard[i][0] != ' ') {
                return true;
            }
        }
//        checking columns
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i] && gameBoard[0][i] != ' ') {
                return true;
            }
        }
//        checking main diagonal
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != ' ') {
            return true;
        }
//        checking secondary diagonal
        if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] != ' ') {
            return true;
        }
        return false;
    }

    public static boolean isDraw(char[][] gameBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}