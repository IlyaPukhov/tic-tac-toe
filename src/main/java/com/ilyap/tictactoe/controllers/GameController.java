package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.entities.Bot;
import com.ilyap.tictactoe.entities.Gamer;
import com.ilyap.tictactoe.utils.GameUtils;
import com.ilyap.tictactoe.TicTacToe;
import com.ilyap.tictactoe.TicTacToeRunner;
import com.ilyap.tictactoe.entities.TicTacToePlayer;
import com.ilyap.tictactoe.exceptions.GameException;
import com.ilyap.tictactoe.exceptions.OpenSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GameController implements SceneSwitchable {

    @FXML
    private GridPane gameField;

    @FXML
    private Text moveTitle;

    @FXML
    private Button repeatButton;

    @FXML
    private Button resultsButton;

    private final TicTacToePlayer player1 = GameUtils.getPlayer1();
    private final TicTacToePlayer player2 = GameUtils.getPlayer2();
    private final TicTacToe ticTacToe = new TicTacToe(3, player1, player2);

    private final List<TicTacToePlayer> players = List.of(player1, player2);

    private TicTacToePlayer currentMovingPlayer = player1;

    private static final String TITLE_START = "Сейчас ходит ";

    @FXML
    void initialize() {
        //TODO() перезапись ячейки запрещена!
        newGame(ticTacToe);
        nextStep(currentMovingPlayer);

        gameField.setOnMouseClicked(this::clickGrid);

        resultsButton.setOnAction(actionEvent -> openNextScene());
        repeatButton.setOnAction(actionEvent -> newGame(ticTacToe));
    }

    private void clickGrid(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        if (clickedNode == gameField || currentMovingPlayer.getClass() == Bot.class) return;

        int x = fixNull(GridPane.getColumnIndex(clickedNode));
        int y = fixNull(GridPane.getRowIndex(clickedNode));

        ((ImageView) clickedNode).setImage(getPlayerSign(currentMovingPlayer));

        move(currentMovingPlayer, x, y);
    }


    //    private void move(Bot player) throws IOException {
//        player.move();
//        lastMovingPlayer = player;
//        if (ticTacToe.isLastMove(player)) {
//            System.out.println("KAVANAUGH");
//        }
//    }
//
    private void move(TicTacToePlayer player, int x, int y) {
        System.out.println("PLAYERMOVE");

        //((Gamer) currentMovingPlayer).move(x, y);

        currentMovingPlayer = players.get(players.size() - (players.indexOf(currentMovingPlayer)) - 1);

        nextStep(currentMovingPlayer);
    }

    private void move(TicTacToePlayer player) {
        System.out.println("BOTMOVE");
        //int[] xy = ((Bot) currentMovingPlayer).move();

        currentMovingPlayer = players.get(players.size() - (players.indexOf(currentMovingPlayer)) - 1);

//        ((ImageView) Objects.requireNonNull(getCurrentNode(xy[0], xy[1])))
//                .setImage(getPlayerSign(currentMovingPlayer));

        nextStep(currentMovingPlayer);
    }

    private void nextStep(TicTacToePlayer player) {
        //TODO() цвета пачины
        if (player.getClass().equals(Bot.class)) {
            moveTitle.setText(TITLE_START + currentMovingPlayer.getName());
            moveTitle.setFill(Paint.valueOf("#345ef3"));
            move(player);
        } else {
            moveTitle.setText(TITLE_START + currentMovingPlayer.getName());
            if (moveTitle.getFill().toString().equals("#f44336")) {
                moveTitle.setFill(Paint.valueOf("#345ef3"));
            } else {
                moveTitle.setFill(Paint.valueOf("#f44336"));
            }
        }

    }

    private Node getCurrentNode(int column, int row) {
        for (Node node : gameField.getChildren()) {
            int columnIndex = fixNull(GridPane.getColumnIndex(node));
            int rowIndex = fixNull(GridPane.getRowIndex(node));

            if (columnIndex == column && rowIndex == row) {
                return node;
            }
        }
        return null;
    }

    private Image getPlayerSign(TicTacToePlayer player) {
        return new Image(Objects.requireNonNull(
                TicTacToeRunner.class.getResourceAsStream(player.getSign().getSignPath())));
    }

    private void newGame(TicTacToe ticTacToe) {
        try {
            ticTacToe.game();
        } catch (IOException e) {
            throw new GameException("Ошибка ведения статистики");
        }
    }

    private int fixNull(Integer el) {
        return el == null ? 0 : el;
    }

    @Override
    public void openNextScene() {
        try {
            GameUtils.openNextScene(resultsButton, "fxml/results.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }
}
