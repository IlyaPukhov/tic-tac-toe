package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.GameUtils;
import com.ilyap.tictactoe.entities.Bot;
import com.ilyap.tictactoe.entities.Gamer;
import com.ilyap.tictactoe.exceptions.OpenSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.ilyap.tictactoe.utils.GameMode.*;

import java.io.IOException;

public class NamingController implements SceneSwitchable {

    @FXML
    private TextField playerOne;

    @FXML
    private TextField playerTwo;

    @FXML
    private Button saveButton;

    @FXML
    private Button returnButton;

    @FXML
    void initialize() {
        if (GameUtils.getGameMode() == PVB) {
            playerOne.setText("Player");
            playerTwo.setEditable(false);
            playerTwo.setDisable(true);
            playerTwo.setText("Bot");
        }

        saveButton.setOnAction(actionEvent -> openNextScene());
        returnButton.setOnAction(actionEvent -> openPreviousScene());
    }

    @Override
    public void openNextScene() {
        GameUtils.setPlayer1(new Gamer(playerOne.getText(), 'x'));

        if (GameUtils.getGameMode() == PVP) {
            GameUtils.setPlayer2(new Gamer(playerTwo.getText(), 'o'));
        } else {
            GameUtils.setPlayer2(new Bot(playerTwo.getText(), 'o'));
        }

        try {
            GameUtils.openNextScene(saveButton, "fxml/game.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }

    public void openPreviousScene() {
        try {
            GameUtils.openNextScene(returnButton, "fxml/start.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }
}
