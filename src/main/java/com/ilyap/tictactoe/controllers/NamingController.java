package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.entities.Bot;
import com.ilyap.tictactoe.entities.Gamer;
import com.ilyap.tictactoe.exceptions.OpenSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import com.ilyap.tictactoe.utils.GameUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.ilyap.tictactoe.states.CellState.CROSS;
import static com.ilyap.tictactoe.states.CellState.NOUGHT;
import static com.ilyap.tictactoe.states.GameMode.PVB;
import static com.ilyap.tictactoe.states.GameMode.PVP;

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
            playerOne.setPromptText("Player");
            playerTwo.setDisable(true);
            playerTwo.setPromptText("Bot");
        }

        saveButton.setOnAction(actionEvent -> openNextScene());
        returnButton.setOnAction(actionEvent -> openPreviousScene());
    }

    @Override
    public void openNextScene() {
        GameUtils.setPlayer1(new Gamer(getName(playerOne), CROSS));

        if (GameUtils.getGameMode() == PVP) {
            GameUtils.setPlayer2(new Gamer(getName(playerTwo), NOUGHT));
        } else {
            GameUtils.setPlayer2(new Bot(getName(playerTwo), NOUGHT));
        }

        try {
            GameUtils.openNextScene(saveButton, "fxml/game.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }

    private String getName(TextField field) {
        String name = field.getText().equals("") ? field.getPromptText() : field.getText();
        int maxLength = Math.min(name.length(), 14);
        return name.substring(0, maxLength);
    }

    public void openPreviousScene() {
        try {
            GameUtils.openNextScene(returnButton, "fxml/start.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }
}