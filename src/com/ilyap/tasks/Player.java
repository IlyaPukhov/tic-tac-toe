package com.ilyap.tasks;

import java.util.Scanner;

import static com.ilyap.tasks.TicTacToe.matrix;

public class Player {
    private final String name;
    private final char sign;
    static int num = 0;
    private final Scanner scanner = new Scanner(System.in);
    private int countWins = 0;

    Player(String name) {
        this.name = name;
        if (num == 0) {
            sign = 'x';
        } else {
            sign = 'o';
        }
        num++;
    }

    void move() {
        System.out.println("Ходит " + name);
        int x, y;
        do {
            System.out.println("Введите X и Y ячейки (1.." + matrix.length + "): ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            if (!TicTacToe.isCellEmpty(y, x)) {
                System.out.println("Данная ячейка занята или находится за границами поля, попробуйте снова...");
            }
        } while (!TicTacToe.isCellEmpty(y, x));
        matrix[y][x] = sign;
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
