package com.example.BookMyShow.repository;

import com.example.BookMyShow.models.Show;
import com.example.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    Optional<ShowSeat> findById(Long showSeatId);

    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    @Override
    ShowSeat save(ShowSeat showSeat);
}
