package com.example.BookMyShow.exception;

public class InvalidShowException extends Exception {
    public InvalidShowException(String message) {
        super(message);
    }

    public InvalidShowException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidShowException(Throwable cause) {
        super(cause);
    }
}
