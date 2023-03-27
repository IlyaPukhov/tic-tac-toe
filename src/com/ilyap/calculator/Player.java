package com.ilyap.calculator;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Player {
    private final String name;
    private final char sign;
    static int num = 0;
    private int countWins = 0;

    public Player(String name) {
        this.name = name;
        if (num == 0) {
            sign = 'x';
        } else {
            sign = 'o';
        }
        num++;
    }

    public void move() {
        System.out.println("Ходит " + name);
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Введите X и Y ячейки (1.." + TicTacToe.matrix.length + "): ");
            try {
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } catch (NoSuchElementException e) {
                System.out.println("Некорректный ввод, попробуйте снова...");
                x = -1;
                y = -1;
                scanner = new Scanner(System.in);
                continue;
            }
            if (!TicTacToe.isCellEmpty(y, x)) {
                System.out.println("Данная ячейка занята или находится за границами поля, попробуйте снова...");
            }
        } while (!TicTacToe.isCellEmpty(y, x));
        TicTacToe.matrix[y][x] = sign;
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
