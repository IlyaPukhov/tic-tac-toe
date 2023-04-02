package com.ilyap.tictactoe.controllers;

import com.ilyap.tictactoe.TicTacToe;
import com.ilyap.tictactoe.TicTacToeRunner;
import com.ilyap.tictactoe.entities.Bot;
import com.ilyap.tictactoe.entities.Gamer;
import com.ilyap.tictactoe.entities.TicTacToePlayer;
import com.ilyap.tictactoe.exceptions.GameException;
import com.ilyap.tictactoe.exceptions.OpenSceneException;
import com.ilyap.tictactoe.interfaces.SceneSwitchable;
import com.ilyap.tictactoe.utils.GameUtils;
import com.ilyap.tictactoe.utils.PlayerState;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private final List<TicTacToePlayer> players = List.of(player1, player2);

    private TicTacToePlayer currentMovingPlayer;
    private static final String TITLE_START = "Сейчас ходит ";

    private final TicTacToe ticTacToe = new TicTacToe(3, player1, player2);

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @FXML
    void initialize() {
        newGame(ticTacToe);

        gameField.setOnMouseClicked(mouseEvent -> {
            try {
                clickGrid(mouseEvent);
            } catch (IOException e) {
                throw new GameException(e.getCause());
            }
        });

        resultsButton.setOnAction(actionEvent -> openNextScene());
        repeatButton.setOnAction(actionEvent -> newGame(ticTacToe));
    }

    private void clickGrid(MouseEvent mouseEvent) throws IOException {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        if (clickedNode == gameField || currentMovingPlayer.getClass() == Bot.class) return;

        int x = fixNull(GridPane.getColumnIndex(clickedNode));
        int y = fixNull(GridPane.getRowIndex(clickedNode));

        ((ImageView) clickedNode).setImage(getPlayerSign(currentMovingPlayer));
        clickedNode.setDisable(true);
        gamerMove(x, y);
    }

    private void gamerMove(int x, int y) throws IOException {
        ((Gamer) currentMovingPlayer).move(x, y);
        nextStep();
    }

    private void botMove() {
        int[] xy = ((Bot) currentMovingPlayer).move();
        Node node = getCurrentNode(xy[0], xy[1]);

        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                ((ImageView) Objects.requireNonNull(node)).setImage(getPlayerSign(currentMovingPlayer));
                node.setDisable(true);
                nextStep();
            } catch (InterruptedException | IOException e) {
                throw new GameException(e.getCause());
            }
        });
    }

    private void nextStep() throws IOException {
        if (checkWinner(currentMovingPlayer)) {
            winnerRoutines();
        } else {
            currentMovingPlayer = players.get(players.size() - (players.indexOf(currentMovingPlayer)) - 1);
            movingRoutines();
        }
    }

    private void movingRoutines() {
        if (currentMovingPlayer.getClass().equals(Bot.class)) {
            moveTitle.setText(TITLE_START + currentMovingPlayer.getName());
            moveTitle.setFill(Paint.valueOf("#345ef3"));
            botMove();
        } else {
            moveTitle.setText(TITLE_START + currentMovingPlayer.getName());
            if (currentMovingPlayer == player1) {
                moveTitle.setFill(Paint.valueOf("#f44336"));
            } else {
                moveTitle.setFill(Paint.valueOf("#345ef3"));
            }
        }
    }

    private void winnerRoutines() {
        moveTitle.setText(currentMovingPlayer.getName() + " победил!");
        gameField.setDisable(true);
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

    private boolean checkWinner(TicTacToePlayer player) throws IOException {
        return ticTacToe.checkWin(player) == PlayerState.WON;
    }

    private void newGame(TicTacToe ticTacToe) {
        ticTacToe.game();
        gameField.setDisable(false);
        for (Node node : gameField.getChildren()) {
            node.setDisable(false);
            ((ImageView) node).setImage(null);
        }
        currentMovingPlayer = player1;
        movingRoutines();
    }

    private int fixNull(Integer el) {
        return el == null ? 0 : el;
    }

    @Override
    public void openNextScene() {
        try {
            executorService.shutdown();
            GameUtils.openNextScene(resultsButton, "fxml/results.fxml");
        } catch (IOException e) {
            throw new OpenSceneException(e.getCause());
        }
    }
}
