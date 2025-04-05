package ticTacToe.strategies.WinningStrategy;

import ticTacToe.models.Board;
import ticTacToe.models.Move;
import ticTacToe.models.Symbol;
import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements  WinningStrategy {
    Map<Integer, Map<Symbol, Integer>> countMap = new HashMap<>();
    @Override
    public boolean checkWinningCondition(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        // Initialize the count map for the row if it doesn't exist
        if (!countMap.containsKey(row)) {
            countMap.put(row, new HashMap<>());
        }
        // Increment the count for the symbol in the row
        Map<Symbol, Integer> symbolCount = countMap.get(row);
        symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
        // Check if the symbol count in the row is equal to the board size
        return symbolCount.get(symbol) == board.getSize();
    }

    @Override
    public void undoMove(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        // Decrement the count for the symbol in the row
        if (countMap.containsKey(row)) {
            Map<Symbol, Integer> symbolCount = countMap.get(row);
            if (symbolCount.containsKey(symbol)) {
                int count = symbolCount.get(symbol);
                if (count > 0) {
                    symbolCount.put(symbol, count - 1);
                }
            }
        }
    }
}
