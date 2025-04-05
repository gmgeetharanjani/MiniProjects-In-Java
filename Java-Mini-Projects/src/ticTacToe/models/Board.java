package ticTacToe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int dimension) {
        this.size = dimension;
        this.board = initializeBoard(dimension);
    }

    private List<List<Cell>> initializeBoard(int dimension) {
        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                row.add(new Cell(i, j));
            }
            board.add(row);
        }
        return board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCells() {
        return board;
    }

    public void setCells(List<List<Cell>> board) {
        this.board = board;
    }

    public void displayBoard() {
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                if(cell.getPlayer() == null) {
                    System.out.print(" | - | ");
                } else {
                    System.out.print(" | " + cell.getPlayer().getSymbol().getaChar() + " | ");
                }
            }
            System.out.println();
        }
    }
}
