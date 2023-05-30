package com.ilyap.tictactoe.states;

public enum CellState {
    EMPTY(null), CROSS("assets/X.png"), NOUGHT("assets/O.png");

    private final String signPath;

    CellState(String signPath) {
        this.signPath = signPath;
    }

    public String getSignPath() {
        return signPath;
    }
}