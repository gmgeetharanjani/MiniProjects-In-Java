package ticTacToe.models;

import ticTacToe.exceptions.BotCountException;
import ticTacToe.exceptions.PlayerCountException;
import ticTacToe.exceptions.SymbolCountException;
import ticTacToe.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private int dimension;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerIndex;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;


        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder winningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws PlayerCountException, BotCountException, SymbolCountException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }

        private void validate() throws PlayerCountException, BotCountException, SymbolCountException {
            validatePlayerCount();
            validateBotCount();
            validateSymbolCount();
        }

        private void validatePlayerCount() throws PlayerCountException {
            if (players == null || players.size() != dimension - 1) {
                throw new PlayerCountException("At least " + (dimension - 1) + " players are required.");
            }
        }

        private void validateBotCount() throws BotCountException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType() == PlayerType.BOT) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                throw new BotCountException("Only one bot is allowed.");
            }
        }

        private void validateSymbolCount() throws SymbolCountException {
            Map<Character, Integer> symbolCount = new HashMap<>();
            for (Player player : players) {
                char symbol = player.getSymbol().getaChar();
                symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
                if (symbolCount.get(symbol) > 1) {
                    throw new SymbolCountException("Duplicate symbols are not allowed.");
                }
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn.");
        Move move = currentPlayer.makeMove(this.board);

        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        boolean isMoveValid = validateMove(row, col, board);
        if (!isMoveValid) {
            makeMove();
            return;
        }
        Cell actualCell = board.getCells().get(row).get(col);
        actualCell.setPlayer(currentPlayer);
        actualCell.setCellState(CellState.FILLED);
        Move actualMove = new Move(actualCell, currentPlayer);
        System.out.println("Player " + currentPlayer.getName() + " made a move at (" + row + ", " + col + ")");

        moves.add(actualMove);
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        if (checkWinner(actualMove)) {
            setGameState(GameState.WIN);
            setWinner(currentPlayer);
            return;
        }

        if(moves.size() == board.getSize() * board.getSize()) {
            setGameState(GameState.DRAW);
        }
    }

    public boolean validateMove(int row, int col, Board board) {
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        Cell cell = board.getCells().get(row).get(col);
        if (cell.getPlayer() != null) {
            System.out.println("Cell already occupied. Try again.");
            return false;
        }
        return true;
    }

    public boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinningCondition(this.board, move)) {
                return true;
            }
        }
        return false;
    }

    public void undoMove() {
        if (moves.isEmpty()) {
            System.out.println("No moves to undo.");
            return;
        }
        Move lastMove = moves.remove(moves.size() - 1);
        for(WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.undoMove(this.board, lastMove);
        }
        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);
        nextPlayerIndex = (nextPlayerIndex - 1 + players.size()) % players.size();
    }
}
