package ticTacToe.strategies.BotPlayingStrategy;

import ticTacToe.models.Move;
import ticTacToe.models.Board;

public interface BotPlayingStrategy {
    public Move makeMove (Board board);
}
