package com.swapnil.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class BookMovieResponseDto {
    private ResponseStatus responseStatus;
    private Long bookingId;
    private int amount;
}
