package ticTacToe.exceptions;

public class BotCountException extends Exception {
    public BotCountException(String message) {
        super(message);
    }

    public BotCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
