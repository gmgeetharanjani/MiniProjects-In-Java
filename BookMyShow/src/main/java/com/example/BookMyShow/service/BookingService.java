package com.example.BookMyShow.service;


import com.example.BookMyShow.exception.InvalidShowException;
import com.example.BookMyShow.exception.InvalidShowSeatException;
import com.example.BookMyShow.exception.InvalidUserException;
import com.example.BookMyShow.models.*;
import com.example.BookMyShow.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.BookMyShow.repository.UserRepository;
import com.example.BookMyShow.repository.ShowRepository;
import com.example.BookMyShow.repository.ShowSeatRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository, PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    // Method to book a movie
    @Transactional(isolation = Isolation.SERIALIZABLE) // Use SERIALIZABLE isolation level to prevent dirty reads
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws InvalidUserException, InvalidShowException, InvalidShowSeatException {
        /* Steps:
        // --- TAKE A LOCK ON THE SHOW SEATS (APPROACH 1) ---
        1. Get user from userId
        2. Get show from showId
        3. Get the ShowSeats with showSeatIds.
        4. TAKE A LOCK ON THE SHOW SEATS (APPROACH 2)
        5. Check if the show seats are available. If not throw exception. If yes mark them as Blocked.
        6. Save the blocked showseats to DB
        7. RELEASE LOCK ON THE SHOW SEATS (APPROACH 2)
        8. Create a booking with PENDING status
        9. If payment is successful, update the booking status to CONFIRMED. Mark the show seats as booked.
           If payment fails, update the booking status to FAILED. Mark the show seats as available.
        10. Return the booking details
        // --- RELEASE LOCK ON THE SHOW SEATS (APPROACH 1) ---
         */

        // 1. Get user from userId
        Optional<User> optionalUser = userRepository.findById(userId);
        // 2. Get show from showId
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalUser.isEmpty()) {
            throw new InvalidUserException("Invalid user ID");
        }
        if (optionalShow.isEmpty()) {
            throw new InvalidShowException("Invalid show ID");
        }

        User user = optionalUser.get();
        Show show = optionalShow.get();
        // 3. Get the ShowSeats with showSeatIds.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        List<ShowSeat> updatedShowSeats = new ArrayList<>();

        // 5. Check if the show seats are available. If not throw exception. If yes mark them as booked.
        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getSeatStatus() != SeatStatus.AVAILABLE) {
                throw new InvalidShowSeatException("Show seat is not available");
            }
        }
        for (ShowSeat showSeat : showSeats) {
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
            // 6. Save the blocked showseats to DB
            updatedShowSeats.add(showSeatRepository.save(showSeat));
        }
        // 8. Create a booking with PENDING status
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setSeats(updatedShowSeats);
        booking.setBookingTime(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setPayments(new ArrayList<>()); // Initialize payments list to empty for now.
        booking.setPrice(priceCalculatorService.calculatePrice(show, updatedShowSeats));
        bookingRepository.save(booking);

        // 10. Return the booking details
        return booking;
    }
}
