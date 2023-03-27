package com.ilyap.tictactoe.utils;

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

    public final int TIMER_INTERVAL = 60;

    public static void openNextScene(Button button, String path) throws IOException {
        ((Stage) (button.getScene().getWindow())).close();
        GameUtils.setWindowScene(new Stage(), path);
    }

    public static void setWindowScene(Stage stage, String path) throws IOException {
        stage.setOnCloseRequest(t -> System.exit(0));

        Parent root = FXMLLoader.load(Objects.requireNonNull(GameUtils.class.getResource(path)));
        stage.getIcons().add(
                new Image(Objects.requireNonNull(GameUtils.class.getResourceAsStream("assets/icon.png"))));
        stage.setTitle("IPv4 адресация");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
