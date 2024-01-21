package com.swapnil.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class SignUpRequestDto {
    private String email;
    private String password;
}
