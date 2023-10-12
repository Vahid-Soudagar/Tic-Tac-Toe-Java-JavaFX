package com.example.tictactoe;

// Import statements for JavaFX components

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

// Main class for the Tic Tac Toe application
public class TicTacToe extends Application {

    // 2D array to represent the game board buttons
    private Button[][] buttons = new Button[3][3];

    // Labels to display scores
    private Label scoreXLabel, score0Label;

    // Boolean to track the current player's turn
    private boolean playerXTurn = true;

    // Scores for players X and O
    private int xScore = 0;
    private int oScore = 0;

    // Method to create the main content of the application
    private BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Title label for the game
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        // Game board represented by a GridPane
        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(10);
        boardGrid.setVgap(10);
        boardGrid.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
                button.setOnAction(event -> buttonClicked(button));
                buttons[i][j] = button;
                boardGrid.add(button, j, i);
            }
        }
        root.setCenter(boardGrid);

        // Score display at the bottom
        HBox scoreBoard = new HBox(20);
        scoreXLabel = new Label("Player X : 0");
        scoreXLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        score0Label = new Label("Player O : 0");
        score0Label.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(scoreXLabel, score0Label);
        scoreBoard.setAlignment(Pos.CENTER);
        root.setBottom(scoreBoard);

        return root;
    }

    // Method to handle button clicks on the game board
    private void buttonClicked(Button button) {
        if (button.getText().equals("")) {
            if (playerXTurn) {
                button.setTextFill(Color.RED);
                button.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold;");
                button.setText("X");
            } else {
                button.setTextFill(Color.BLUE);
                button.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold;");
                button.setText("O");
            }
            playerXTurn = !playerXTurn;
            checkWinner();
        }
    }

    // Method to check for a winner after each move
    private void checkWinner() {
        // Check for a win in rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()) {
                String winner = buttons[row][0].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // Check for a win in columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()) {
                String winner = buttons[0][col].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // Check for a win in diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            String winner = buttons[0][0].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty()) {
            String winner = buttons[0][2].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        // Check for a tie
        boolean tie = true;
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
        }

        if (tie) {
            showTieDialogue();
            resetBoard();
        }
    }

    // Method to display a dialogue for the winner
    private void showWinnerDialogue(String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNER");
        alert.setContentText("Congratulations Player " + winner + " You won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    // Method to display a dialogue for a tie
    private void showTieDialogue() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TIE");
        alert.setContentText("Game Over It's a Tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    // Method to update the scores based on the winner
    private void updateScore(String winner) {
        if (winner.equals("X")) {
            xScore++;
            scoreXLabel.setText("Player X : " + xScore);
        } else {
            oScore++;
            score0Label.setText("Player O : " + oScore);
        }
    }

    // Method to reset the game board
    private void resetBoard() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");
            }
        }
    }

    // The main method to launch the application
        @Override
        public void start (Stage stage) throws IOException {
            Scene scene = new Scene(createContent());
            stage.setTitle("Tic Tac Toe");
            stage.setScene(scene);
            stage.show();
        }

        public static void main (String[]args){
            launch();
        }
    }