package com.ilyap.tictactoe;

import com.ilyap.tictactoe.entities.TicTacToePlayer;
import com.ilyap.tictactoe.utils.GameMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Objects;

@UtilityClass
public final class GameUtils {
    private GameMode gameMode;

    private TicTacToePlayer player1;
    private TicTacToePlayer player2;

    public void openNextScene(Button button, String path) throws IOException {
        button.getScene().getWindow().hide();
        GameUtils.setWindowScene(new Stage(), path);
    }

    public void openNextScene(Stage stage, String path) throws IOException {
        GameUtils.setWindowScene(stage, path);
    }

    private void setWindowScene(Stage stage, String path) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(GameUtils.class.getResource(path)));
        stage.getIcons().add(
                new Image(Objects.requireNonNull(GameUtils.class.getResourceAsStream("assets/icon.png"))));
        stage.setTitle("Крестики-нолики");
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        GameUtils.gameMode = gameMode;
    }

    public TicTacToePlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(TicTacToePlayer player1) {
        GameUtils.player1 = player1;
    }

    public TicTacToePlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(TicTacToePlayer player2) {
        GameUtils.player2 = player2;
    }
}
