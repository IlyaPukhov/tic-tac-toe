package com.ilyap.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class TicTacToe {
    static char[][] matrix;
    private static final char EMPTY_CELL = '—';

    private final Player player1;
    private final Player player2;

    public TicTacToe() {
        this(3, new Player("PlayerOne"), new Player("PlayerTwo"));
    }

    public TicTacToe(int size, Player player1, Player player2) {
        matrix = new char[size][size];
        this.player1 = player1;
        this.player2 = player2;
    }

    public void game() throws IOException {
        initMatrix();
        printCurrentMatrix();
        while (true) {
            player1.move();
            printCurrentMatrix();
            if (isLastMove(player1)) {
                break;
            }

            player2.move();
            printCurrentMatrix();
            if (isLastMove(player2)) {
                break;
            }
        }
        writeRating(player1, player2);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Хотите продолжить? ");
            if (scanner.next().equalsIgnoreCase("да")) {
                restartGame();
            } else {
                System.out.println("GAME OVER.");
            }
        }
    }

    private void restartGame() throws IOException {
        game();
    }

    private boolean isLastMove(Player player) {
        if (checkWin(player.getSign())) {
            if (player.equals(player1)) {
                player1.setCountWins(1);
                player2.setCountWins(0);
            } else {
                player1.setCountWins(0);
                player2.setCountWins(1);
            }
            System.out.println(player.getName() + " выиграл!");
            return true;
        }
        if (isMatrixFull()) {
            player1.setCountWins(0);
            player2.setCountWins(0);
            System.out.println("НИЧЬЯ!");
            return true;
        }
        return false;
    }

    private void writeRating(Player... players) throws IOException {
        Files.createDirectories(Path.of("stats"));
        Path path = Path.of("stats", "players-rating.txt");
        List<String> rate = RatingHelper.getNewRate(path, players);
        Files.write(path, rate, CREATE, TRUNCATE_EXISTING);
    }

    private boolean checkWin(char sign) {
        return checkHorizontal(sign) ||
                checkVertical(sign) ||
                checkLeftDiagonal(sign) ||
                checkRightDiagonal(sign);
    }

    private boolean checkHorizontal(char sign) {
        for (char[] chars : matrix) {
            int countSign = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (chars[i] == sign) {
                    countSign++;
                }
                if (countSign == matrix.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char sign) {
        for (int i = 0; i < matrix.length; i++) {
            int countSign = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[j][i] == sign) {
                    countSign++;
                }
                if (countSign == matrix.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLeftDiagonal(char sign) {
        int countSign = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == sign) {
                countSign++;
            }
            if (countSign == matrix.length) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRightDiagonal(char sign) {
        int countSign = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[matrix.length - i - 1][i] == sign) {
                countSign++;
            }

            if (countSign == matrix.length) {
                return true;
            }
        }
        return false;
    }

    private void initMatrix() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++)
                matrix[row][col] = EMPTY_CELL;
        }
    }

    private void printCurrentMatrix() {
        for (char[] chars : matrix) {
            for (char value : chars) {
                System.out.print("| " + value + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println();
    }

    static boolean isCellEmpty(int y, int x) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix.length) return false;
        return matrix[y][x] == EMPTY_CELL;
    }

    private boolean isMatrixFull() {
        for (char[] chars : matrix) {
            for (char value : chars) {
                if (value == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }
}
