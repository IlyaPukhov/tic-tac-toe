package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.GameUtils;
import com.ilyap.tictactoe.TicTacToeRunner;
import com.ilyap.tictactoe.exceptions.NextSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import com.ilyap.tictactoe.utils.GameMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import java.io.IOException;

import static com.ilyap.tictactoe.utils.GameMode.*;

public class StartController implements SceneSwitchable {

    @FXML
    private Button startButton;

    @FXML
    private ToggleButton togglePvB;

    @FXML
    private ToggleButton togglePvP;

    @FXML
    private ImageView logo;

    final String github = "https://github.com/IlyaPukhov";

    @FXML
    void initialize() {
        ToggleGroup group = new ToggleGroup();
        togglePvP.setToggleGroup(group);
        togglePvB.setToggleGroup(group);
        togglePvP.setSelected(true);

        startButton.setOnAction(actionEvent -> openNextScene());
        togglePvP.setOnAction(actionEvent -> toggleSwitch());
        togglePvB.setOnAction(actionEvent -> toggleSwitch());
        logo.setOnMouseClicked(mouseEvent -> TicTacToeRunner.openInBrowser(github));
    }

    private void toggleSwitch() {
        if (togglePvP.isSelected()) {
            togglePvB.getStyleClass().removeAll("toggleEnable");
            togglePvB.getStyleClass().add("toggleDisable");

            togglePvP.toFront();
            togglePvP.getStyleClass().removeAll("toggleDisable");
            togglePvP.getStyleClass().add("toggleEnable");
        } else if (togglePvB.isSelected()) {
            togglePvP.getStyleClass().removeAll("toggleEnable");
            togglePvP.getStyleClass().add("toggleDisable");

            togglePvB.toFront();
            togglePvB.getStyleClass().removeAll("toggleDisable");
            togglePvB.getStyleClass().add("toggleEnable");
        }
    }

    @Override
    public void openNextScene() {
        GameMode gameMode = togglePvP.isSelected() ? PVP : PVB;
        GameUtils.setGameMode(gameMode);
        try {
            GameUtils.openNextScene(startButton, "fxml/naming.fxml");
        } catch (IOException e) {
            throw new NextSceneException(e.getCause());
        }
    }
}
