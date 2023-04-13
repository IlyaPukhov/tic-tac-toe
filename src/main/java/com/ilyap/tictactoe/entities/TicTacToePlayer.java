package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.TicTacToe;

public abstract class TicTacToePlayer {
    private final String name;
    private final CellState sign;
    private TicTacToe game;
    private int countWins = 0;

    public TicTacToePlayer(String name, CellState sign) {
        this.name = name;
        this.sign = sign;
    }

    public void setGame(TicTacToe game) {
        this.game = game;
    }

    public TicTacToe getGame() {
        return game;
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