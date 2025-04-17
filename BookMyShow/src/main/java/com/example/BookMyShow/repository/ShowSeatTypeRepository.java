package com.example.BookMyShow.repository;

import com.example.BookMyShow.models.Show;
import com.example.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    @Override
    List<ShowSeatType> findAllById(Iterable<Long> showSeatTypeIds);

    List<ShowSeatType> findAllByShow(Show show);
}
