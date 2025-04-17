package com.example.BookMyShow.exception;

public class InvalidShowSeatException extends Exception {
    public InvalidShowSeatException(String message) {
        super(message);
    }

    public InvalidShowSeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidShowSeatException(Throwable cause) {
        super(cause);
    }
}
