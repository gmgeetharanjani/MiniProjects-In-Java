package com.example.BookMyShow.models;

public enum ResponseStatus {
    SUCCESS,
    FAILED,
    PENDING,
    CANCELLED,
    REFUNDED,
    PARTIALLY_REFUNDED,
    INVALID_REQUEST,
    USER_NOT_FOUND,
    MOVIE_NOT_FOUND,
    SHOW_NOT_FOUND,
    SEAT_NOT_AVAILABLE,
    PAYMENT_FAILED
}
