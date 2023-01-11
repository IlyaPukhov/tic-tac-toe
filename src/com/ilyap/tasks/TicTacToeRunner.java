package com.ilyap.tasks;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeRunner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            //System.out.println("Введите имена игроков: ");
            TicTacToe ticTacToe = new TicTacToe();
            ticTacToe.game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
