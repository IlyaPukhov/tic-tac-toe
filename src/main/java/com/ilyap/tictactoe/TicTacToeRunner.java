package com.ilyap.tictactoe;

import com.ilyap.tictactoe.utils.GameUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeRunner extends Application {

    private static HostServices hostServices;

    @Override
    public void start(Stage stage) throws IOException {
        hostServices = this.getHostServices();
        GameUtils.openNextScene(stage, "fxml/start.fxml");
    }

    public static void openInBrowser(String url) {
        hostServices.showDocument(url);
    }

    public static void main(String[] args) {
        launch(args);
    }
}