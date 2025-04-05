package ticTacToe.strategies.WinningStrategy;

import ticTacToe.models.Board;
import ticTacToe.models.Move;
import ticTacToe.models.Symbol;
import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    Map<Integer, Map<Symbol, Integer>> countMap = new HashMap<>();
    @Override
    public boolean checkWinningCondition(Board board, Move move) {
        int row = move.getCell().getRow();
        int column = move.getCell().getColumn();
        Symbol symbol = move.getCell().getPlayer().getSymbol();

        // Check for main diagonal
        if (row == column) {
            if (!countMap.containsKey(0)) {
                countMap.put(0, new HashMap<>());
            }
            Map<Symbol, Integer> symbolCount = countMap.get(0);
            symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
            return symbolCount.get(symbol) == board.getSize();
        }

        // Check for anti-diagonal
        if (row + column == board.getSize() - 1) {
            if (!countMap.containsKey(1)) {
                countMap.put(1, new HashMap<>());
            }
            Map<Symbol, Integer> symbolCount = countMap.get(1);
            symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
            return symbolCount.get(symbol) == board.getSize();
        }
        return false;
    }

    @Override
    public void undoMove(Board board, Move move) {
        int row = move.getCell().getRow();
        int column = move.getCell().getColumn();
        Symbol symbol = move.getCell().getPlayer().getSymbol();

        // Decrement count for main diagonal
        if (row == column && countMap.containsKey(0)) {
            Map<Symbol, Integer> symbolCount = countMap.get(0);
            if (symbolCount.containsKey(symbol)) {
                int count = symbolCount.get(symbol);
                if (count > 0) {
                    symbolCount.put(symbol, count - 1);
                }
            }
        }

        // Decrement count for anti-diagonal
        if (row + column == board.getSize() - 1 && countMap.containsKey(1)) {
            Map<Symbol, Integer> symbolCount = countMap.get(1);
            if (symbolCount.containsKey(symbol)) {
                int count = symbolCount.get(symbol);
                if (count > 0) {
                    symbolCount.put(symbol, count - 1);
                }
            }
        }
    }
}
