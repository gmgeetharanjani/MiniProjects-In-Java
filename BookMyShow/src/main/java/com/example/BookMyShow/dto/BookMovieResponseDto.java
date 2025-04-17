package com.example.BookMyShow.dto;


import com.example.BookMyShow.models.BookingStatus;
import com.example.BookMyShow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieResponseDto {
    private Long amount;
    private Long bookingId;
    private ResponseStatus responseStatus;
}
