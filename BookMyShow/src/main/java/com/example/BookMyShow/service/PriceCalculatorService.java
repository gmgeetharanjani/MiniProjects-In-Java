package com.example.BookMyShow.service;

import com.example.BookMyShow.models.Show;
import com.example.BookMyShow.models.ShowSeat;
import com.example.BookMyShow.models.ShowSeatType;
import com.example.BookMyShow.repository.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public Long calculatePrice(Show show, List<ShowSeat> showSeats) {
        long totalPrice = 0L;
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        for(ShowSeat showSeat: showSeats) {
            for(ShowSeatType showSeatType: showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    totalPrice += showSeatType.getPrice();
                    break;
                }
            }
        }
        return totalPrice;
    }
}
