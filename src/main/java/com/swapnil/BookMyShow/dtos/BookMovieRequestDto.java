package com.swapnil.BookMyShow.dtos;

import com.swapnil.BookMyShow.models.Show;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.List;
@Getter
@Setter
@Controller
public class BookMovieRequestDto {
    private Long UserId;
    private Long showID;
    private List<Long> showSeatId;
}
