package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.exceptions.GameException;
import com.ilyap.tictactoe.states.CellState;

public class Gamer extends TicTacToePlayer {
    public Gamer(String name, CellState sign) {
        super(name, sign);
    }

    public void move(int x, int y) {
        if (getGame().isCellEmpty(x, y)) {
            getGame().setMatrix(x, y, this.getSign());
        } else {
            throw new GameException("Данная ячейка занята!");
        }
    }
}