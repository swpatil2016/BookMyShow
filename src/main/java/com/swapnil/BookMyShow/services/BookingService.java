package com.swapnil.BookMyShow.services;

import com.swapnil.BookMyShow.Repositories.BookingRepository;
import com.swapnil.BookMyShow.Repositories.ShowRepository;
import com.swapnil.BookMyShow.Repositories.ShowSeatRepository;
import com.swapnil.BookMyShow.Repositories.UserRepository;
import com.swapnil.BookMyShow.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PricingService pricingService;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PricingService pricingService,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.pricingService = pricingService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatsIds, Long showId){
    /*
    --------------- start the lock here for Today------------------
    * 1. Get the user from the user ID (from userRepository)
    * 2. Get the show from the show ID (from showRepository)
    * -------------Take a lock--------------------------------
    * 3. Get the show seats from the seatIds (from showSeatRepository)
    * 4. Check if the seats are available
    * 5. if yes, make the status as blocked (or booking in progress)
    * ------------ Release the lock-----------------------
    * 6. Save updated show seats in DB and end the lock
    --------------------- End lock here for Today------------------
    * */
        Optional<User> userOptional = userRepository.findById(userId); //1
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }
        User bookedBy = userOptional.get();


        Optional<Show> showOptional = showRepository.findById(showId); //2
        if(showOptional.isEmpty()){
            throw new RuntimeException();
        }
        Show bookedShow = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatsIds); //3

        for(ShowSeat showSeat: showSeats){   //4
            //We can only booked seat when this two conditions are true. Opposite of these condition we can not booked.
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(),
                                    new Date().toInstant()).toMinutes() > 15)) {
                throw new RuntimeException();
            }
        }


        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for (ShowSeat showSeat: showSeats){ //5
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date()); //update the time
            savedShowSeats.add(showSeatRepository.save(showSeat)); //6
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setUser(bookedBy);
        booking.setShow(bookedShow);
        booking.setBookedAt(new Date());
        booking.setAmount(pricingService.calculatePrice(savedShowSeats, bookedShow));

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}
