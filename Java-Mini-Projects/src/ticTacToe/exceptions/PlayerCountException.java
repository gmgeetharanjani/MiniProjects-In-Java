package ticTacToe.exceptions;

public class PlayerCountException extends Exception {
    public PlayerCountException(String message) {
        super(message);
    }

    public PlayerCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
