package com.ilyap.tictactoe;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeRunner extends Application {
    //    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            System.out.println("Введите имена игроков: ");
//            TicTacToe ticTacToe = new TicTacToe(3, new Player(scanner.next()), new Player(scanner.next()));
//            ticTacToe.game();
//        } catch (IOException e) {
//            throw new GameException("Ошибка ведения статистики");
//        }
//    }
    private static HostServices hostServices;

    @Override
    public void start(Stage stage) throws IOException {
        hostServices = this.getHostServices();
        GameUtils.setWindowScene(stage, "fxml/start.fxml");
    }

    public static void openInBrowser(String url) {
        hostServices.showDocument(url);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
