package ticTacToe.strategies.WinningStrategy;

import ticTacToe.models.Board;
import ticTacToe.models.Move;
import ticTacToe.models.Symbol;
import java.util.HashMap;
import java.util.Map;
/**
 * This class implements the WinningStrategy interface and checks for winning conditions in columns.
 */

public class ColumnWinningStrategy implements  WinningStrategy {

    Map<Integer, Map<Symbol, Integer>> countMap = new HashMap<>();
    @Override
    public boolean checkWinningCondition(Board board, Move move) {
        int column = move.getCell().getColumn();
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        // Initialize the count map for the column if it doesn't exist
        if (!countMap.containsKey(column)) {
            countMap.put(column, new HashMap<>());
        }
        // Increment the count for the symbol in the column
        Map<Symbol, Integer> symbolCount = countMap.get(column);
        symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
        // Check if the symbol count in the column is equal to the board size
        return symbolCount.get(symbol) == board.getSize();
    }

    @Override
    public void undoMove(Board board, Move move) {
        int column = move.getCell().getColumn();
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        // Decrement the count for the symbol in the column
        if (countMap.containsKey(column)) {
            Map<Symbol, Integer> symbolCount = countMap.get(column);
            if (symbolCount.containsKey(symbol)) {
                int count = symbolCount.get(symbol);
                if (count > 0) {
                    symbolCount.put(symbol, count - 1);
                }
            }
        }
    }
}
