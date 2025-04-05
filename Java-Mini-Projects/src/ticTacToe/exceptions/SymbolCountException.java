package ticTacToe.exceptions;

public class SymbolCountException extends Exception {
    public SymbolCountException(String message) {
        super(message);
    }

    public SymbolCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
