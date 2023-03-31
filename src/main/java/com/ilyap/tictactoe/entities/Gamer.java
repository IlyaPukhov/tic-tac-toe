package com.ilyap.tictactoe.entities;

import com.ilyap.tictactoe.TicTacToe;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Gamer extends TicTacToePlayer {
    public Gamer(String name, char sign) {
        super(name, sign);
    }

    @Override
    public void move() {
        System.out.println("Ходит " + this.getName());
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Введите X и Y ячейки (1.." + TicTacToe.getMatrix().length + "): ");
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
            if (TicTacToe.isCellFilled(y, x)) {
                System.out.println("Данная ячейка занята или находится за границами поля, попробуйте снова...");
            }
        } while (TicTacToe.isCellFilled(y, x));
        TicTacToe.setMatrix(x, y, this.getSign());
    }
}
