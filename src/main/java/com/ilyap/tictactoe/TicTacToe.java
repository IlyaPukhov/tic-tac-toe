package com.ilyap.tictactoe;

import com.ilyap.tictactoe.states.CellState;
import com.ilyap.tictactoe.entities.TicTacToePlayer;
import com.ilyap.tictactoe.exceptions.GameException;
import com.ilyap.tictactoe.states.GameState;
import com.ilyap.tictactoe.utils.RatingHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.ilyap.tictactoe.states.CellState.EMPTY;
import static com.ilyap.tictactoe.states.GameState.*;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class TicTacToe {
    private final CellState[][] matrix;

    private final TicTacToePlayer player1;
    private final TicTacToePlayer player2;

    public TicTacToe(int size, TicTacToePlayer player1, TicTacToePlayer player2) {
        matrix = new CellState[size][size];
        this.player1 = player1;
        this.player2 = player2;
    }

    public void game() {
        initMatrix();
    }

    public GameState checkWin(TicTacToePlayer player) throws IOException {
        if (checkWin(player.getSign())) {
            if (player.equals(player1)) {
                player1.setCountWins(1);
                player2.setCountWins(0);
            } else {
                player1.setCountWins(0);
                player2.setCountWins(1);
            }
            writeRating(player1, player2);
            return WON;
        } else {
            if (isMatrixFull()) {
                player1.setCountWins(0);
                player2.setCountWins(0);
                writeRating(player1, player2);
                return DRAW;
            }
        }
        return PROCESS;
    }

    private void writeRating(TicTacToePlayer... players) throws IOException {
        Files.createDirectories(Path.of("stats"));
        Path path = Path.of("stats", "players-rating.txt");

        List<String> rateList = RatingHelper.getNewRate(path, players);
        String rateString = String.join("\n", rateList);
        Files.writeString(path, rateString, CREATE, TRUNCATE_EXISTING);
    }

    private boolean checkWin(CellState sign) {
        return checkHorizontal(sign) ||
                checkVertical(sign) ||
                checkLeftDiagonal(sign) ||
                checkRightDiagonal(sign);
    }

    private boolean checkHorizontal(CellState sign) {
        for (CellState[] cells : matrix) {
            int countSign = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (cells[i] == sign) {
                    countSign++;
                }
                if (countSign == matrix.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(CellState sign) {
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

    private boolean checkLeftDiagonal(CellState sign) {
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

    private boolean checkRightDiagonal(CellState sign) {
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
                matrix[row][col] = EMPTY;
        }
    }

    public boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix.length) {
            throw new GameException("Неверные координаты!");
        }
        return matrix[y][x] == EMPTY;
    }

    private boolean isMatrixFull() {
        for (CellState[] cells : matrix) {
            for (CellState value : cells) {
                if (value == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setMatrix(int x, int y, CellState sign) {
        matrix[y][x] = sign;
    }
}