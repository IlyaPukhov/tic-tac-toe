package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.TicTacToe;

public class Gamer extends TicTacToePlayer {
    public Gamer(String name, CellState sign) {
        super(name, sign);
    }

    public void move(int x, int y) {
        do {
            if (TicTacToe.isCellFilled(y, x)) {
                System.out.println("Данная ячейка занята или находится за границами поля, попробуйте снова...");
            }
        } while (TicTacToe.isCellFilled(y, x));
        TicTacToe.setMatrix(x, y, this.getSign());
    }
}
