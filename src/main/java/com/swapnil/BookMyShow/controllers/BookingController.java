package com.swapnil.BookMyShow.controllers;

import com.swapnil.BookMyShow.dtos.BookMovieRequestDto;
import com.swapnil.BookMyShow.dtos.BookMovieResponseDto;
import com.swapnil.BookMyShow.dtos.ResponseStatus;
import com.swapnil.BookMyShow.models.Booking;
import com.swapnil.BookMyShow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        Booking booking;
        try {
            booking = bookingService.bookMovie(bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowSeatId(),
                    bookMovieRequestDto.getShowID()
            );
            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getAmount());
        }catch (Exception e){
            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookMovieResponseDto;
    }

}
