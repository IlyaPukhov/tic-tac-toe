package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.TicTacToe;
import com.ilyap.tictactoe.exceptions.GameException;

public class Gamer extends TicTacToePlayer {
    public Gamer(String name, CellState sign) {
        super(name, sign);
    }

    public void move(int x, int y) {
        if (TicTacToe.isCellEmpty(x, y)) {
            TicTacToe.setMatrix(x, y, this.getSign());
        } else {
            throw new GameException("Данная ячейка занята!");
        }
    }
}
