package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.exceptions.OpenSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import com.ilyap.tictactoe.utils.GameUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultsController implements SceneSwitchable {

    @FXML
    private Button restartButton;

    @FXML
    private TableView<?> resultsTable;

    @FXML
    private Button returnButton;

    @FXML
    void initialize() {
        fillTable();
    }

    private void fillTable() {
        // sample data
        Map<String, String> map = new HashMap<>();
        map.put("one", "One");
        map.put("two", "Two");
        map.put("three", "Three");


        // use fully detailed type for Map.Entry<String, String>
        TableColumn<Map.Entry<String, String>, String> column1 = new TableColumn<>("Key");
        column1.setCellValueFactory(p -> {
            // this callback returns property for just one cell, you can't use a loop here
            // for first column we use key
            return new SimpleStringProperty(p.getValue().getKey());
        });

        TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>("Value");
        column2.setCellValueFactory(p -> {
            // for second column we use value
            return new SimpleStringProperty(p.getValue().getValue());
        });

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
        final TableView<Map.Entry<String,String>> table = new TableView<>(items);

        table.getColumns().setAll(column1, column2);
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