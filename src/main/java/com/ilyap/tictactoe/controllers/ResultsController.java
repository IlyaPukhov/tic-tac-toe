package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.exceptions.GameException;
import com.ilyap.tictactoe.exceptions.OpenSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import com.ilyap.tictactoe.utils.GameUtils;
import com.ilyap.tictactoe.utils.RatingHelper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class ResultsController implements SceneSwitchable {

    @FXML
    private Button restartButton;

    @FXML
    private Button returnButton;

    @FXML
    private TableView<Map.Entry<String, Integer>> resultsTable;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> nameColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> winsColumn;

    @FXML
    void initialize() {
        try {
            fillTable();
        } catch (IOException e) {
            throw new GameException(e.getCause());
        }

        restartButton.setOnAction(actionEvent -> openNextScene());
        returnButton.setOnAction(actionEvent -> openPreviousScene());
    }

    private void fillTable() throws IOException {

        Map<String, Integer> map = RatingHelper.getCurrentRate(Path.of("stats", "players-rating.txt"));

        nameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        winsColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(map.entrySet());
        resultsTable.setItems(items);
    }

    @Override
    public void openNextScene() {
        try {
            GameUtils.openNextScene(restartButton, "fxml/start.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }

    public void openPreviousScene() {
        try {
            GameUtils.openNextScene(returnButton, "fxml/game.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }
}