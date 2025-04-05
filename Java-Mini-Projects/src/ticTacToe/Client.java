package ticTacToe;

import ticTacToe.controllers.GameController;
import ticTacToe.models.*;
import ticTacToe.strategies.WinningStrategy.*;

import java.util.ArrayList;
import java.util.List;


public class Client {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        // Initialize the game
        try {
            int dimension = 3;
            List<Player> players = new ArrayList<>();
            players.add(new Player(1L, "Player 1", new Symbol('X'), PlayerType.HUMAN));
            players.add(new Bot(2L, "Bot 1", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY));
            // players.add(new Player(2L, "Player 2", new Symbol('O'), PlayerType.HUMAN));
            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());
            Game game = gameController.startGame(3, players, winningStrategies);
            System.out.println("Game started successfully!");

            while(game.getGameState() == GameState.IN_PROGRESS) {
                // Display the board
                gameController.displayBoard(game);

                // Get the current player
                Player currentPlayer = players.get(game.getNextPlayerIndex());

                // Check if user wants to undo the move
                if (currentPlayer.getPlayerType() == PlayerType.HUMAN) {
                    System.out.println("Do you want to undo the last move? (y/n)");
                    String response = new java.util.Scanner(System.in).nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        gameController.undoMove(game);
                        System.out.println("Last move undone.");
                        continue;
                    }
                }

                // Make a move
                gameController.makeMove(game);

                // Check game state
                if (gameController.checkGameState(game) == GameState.WIN) {
                    System.out.println("Player " + currentPlayer.getName() + " wins!");
                    game.setWinner(currentPlayer);
                    break;
                } else if (gameController.checkGameState(game) == GameState.DRAW) {
                    System.out.println("It's a draw!");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error starting game: " + e.getMessage());
            return;
        }
    }
}
