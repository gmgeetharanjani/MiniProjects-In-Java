package ticTacToe.strategies.WinningStrategy;
import ticTacToe.models.Board;
import ticTacToe.models.Move;

public interface WinningStrategy {
    public boolean checkWinningCondition(Board board, Move move);
    public void undoMove(Board board, Move move);
}
