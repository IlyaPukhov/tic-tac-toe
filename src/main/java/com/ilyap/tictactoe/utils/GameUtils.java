package com.ilyap.tictactoe.utils;

import com.ilyap.tictactoe.TicTacToeRunner;
import com.ilyap.tictactoe.entities.TicTacToePlayer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Objects;

@UtilityClass
public final class GameUtils {

    @Getter
    @Setter
    private GameMode gameMode;

    @Getter
    @Setter
    private TicTacToePlayer player1;

    @Getter
    @Setter
    private TicTacToePlayer player2;

    public void openNextScene(Button button, String path) throws IOException {
        button.getScene().getWindow().hide();
        GameUtils.setWindowScene(new Stage(), path);
    }

    public void openNextScene(Stage stage, String path) throws IOException {
        GameUtils.setWindowScene(stage, path);
    }

    private void setWindowScene(Stage stage, String path) throws IOException {
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        Parent root = FXMLLoader.load(Objects.requireNonNull(TicTacToeRunner.class.getResource(path)));
        stage.getIcons().add(
                new Image(Objects.requireNonNull(TicTacToeRunner.class.getResourceAsStream("assets/icon.png"))));
        stage.setTitle("Крестики-нолики");
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}