package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.BookMovieRequestDto;
import com.example.BookMyShow.dto.BookMovieResponseDto;
import com.example.BookMyShow.models.Booking;
import com.example.BookMyShow.models.ResponseStatus;
import com.example.BookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto) {
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        Long userId = bookMovieRequestDto.getUserId();
        Long showId = bookMovieRequestDto.getShowId();
        List<Long> showSeatIds = bookMovieRequestDto.getShowSeatIds();

        // Validate the request
        if (userId == null || showId == null || showSeatIds == null || showSeatIds.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking request");
        }
        try {
            Booking booking = bookingService.bookMovie(userId, showId, showSeatIds);
            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getPrice());
        } catch (Exception e) {
            // Handle the exception and return an error response
            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return bookMovieResponseDto;
    }
}
