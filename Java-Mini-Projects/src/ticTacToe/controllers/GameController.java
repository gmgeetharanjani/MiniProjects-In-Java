package ticTacToe.controllers;

import ticTacToe.exceptions.BotCountException;
import ticTacToe.exceptions.PlayerCountException;
import ticTacToe.exceptions.SymbolCountException;
import ticTacToe.models.GameState;
import ticTacToe.models.Player;
import ticTacToe.strategies.WinningStrategy.WinningStrategy;
import ticTacToe.models.Game;
import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws PlayerCountException, BotCountException, SymbolCountException {
        return Game.getBuilder()
                .dimension(dimension)
                .players(players)
                .winningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameState checkGameState(Game game) {
        return game.getGameState();
    }

    public void undoMove(Game game) {
        game.undoMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
