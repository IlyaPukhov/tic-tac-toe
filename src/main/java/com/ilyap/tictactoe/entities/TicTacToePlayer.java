package com.ilyap.tictactoe.entities;

public abstract class TicTacToePlayer {
    private final String name;
    private final CellState sign;
    private int countWins = 0;

    public TicTacToePlayer(String name, CellState sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public CellState getSign() {
        return sign;
    }

    public int getCountWins() {
        return countWins;
    }

    public void setCountWins(int countWins) {
        this.countWins = countWins;
    }
}
