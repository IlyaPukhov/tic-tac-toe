package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.states.CellState;

import java.util.Random;

public class Bot extends TicTacToePlayer {
    public Bot(String name, CellState sign) {
        super(name, sign);
    }

    public int[] move() {
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!getGame().isCellEmpty(x, y));

        getGame().setMatrix(x, y, this.getSign());

        return new int[]{x, y};
    }
}