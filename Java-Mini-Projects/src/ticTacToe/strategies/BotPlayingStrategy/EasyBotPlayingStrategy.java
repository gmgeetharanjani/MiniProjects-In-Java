package ticTacToe.strategies.BotPlayingStrategy;

import ticTacToe.models.Cell;
import ticTacToe.models.CellState;
import ticTacToe.models.Move;
import ticTacToe.models.Board;
import java.util.List;

/**
 * This class implements a simple bot playing strategy for Tic Tac Toe.
 * The bot randomly selects one of the available cells to make its move.
 */
public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row : board.getCells()) {
            for(Cell cell : row) {
                if(cell.getCellState() == CellState.EMPTY) {
                    return new Move(cell, cell.getPlayer());
                }
            }
        }
        return null; // No available moves
    }
}
