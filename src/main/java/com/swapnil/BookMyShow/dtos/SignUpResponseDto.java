package com.swapnil.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private Long userId;
}
