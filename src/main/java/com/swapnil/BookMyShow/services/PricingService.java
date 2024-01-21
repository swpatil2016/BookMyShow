package com.swapnil.BookMyShow.services;

import com.swapnil.BookMyShow.Repositories.ShowSeatTypeRepository;
import com.swapnil.BookMyShow.models.Show;
import com.swapnil.BookMyShow.models.ShowSeat;
import com.swapnil.BookMyShow.models.ShowSeatType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    private ShowSeatTypeRepository showSeatTypeRepository;
    int amount = 0;
    public int calculatePrice(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        for(ShowSeat showSeat: showSeats){ // these are selected by user
            for(ShowSeatType showSeatType: showSeatTypes){ // these are coming from DB
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){ // comparing
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }
}
