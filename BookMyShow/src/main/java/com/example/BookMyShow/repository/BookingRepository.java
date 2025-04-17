package com.example.BookMyShow.repository;

import com.example.BookMyShow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Custom query to find a booking by user ID and show ID
    Booking findByUserIdAndShowId(Long userId, Long showId);

    // Custom query to find a booking by booking ID
    @Override
    Optional<Booking> findById(Long bookingId);

    @Override
    Booking save(Booking booking);
}
