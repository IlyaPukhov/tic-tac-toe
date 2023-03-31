package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.interfaces.Movable;

public abstract class TicTacToePlayer implements Movable {
    private final String name;
    private final char sign;
    private int countWins = 0;

    public TicTacToePlayer(String name, char sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public char getSign() {
        return sign;
    }

    public int getCountWins() {
        return countWins;
    }

    public void setCountWins(int countWins) {
        this.countWins = countWins;
    }
}
