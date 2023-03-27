package com.ilyap.calculator;

import com.ilyap.calculator.exception.GameException;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeRunner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите имена игроков: ");
            TicTacToe ticTacToe = new TicTacToe(3, new Player(scanner.next()), new Player(scanner.next()));
            ticTacToe.game();
        } catch (IOException e) {
            throw new GameException("Ошибка ведения статистики");
        }
    }
}
