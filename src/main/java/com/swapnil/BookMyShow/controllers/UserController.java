package com.swapnil.BookMyShow.controllers;

import com.swapnil.BookMyShow.dtos.ResponseStatus;
import com.swapnil.BookMyShow.dtos.SignUpRequestDto;
import com.swapnil.BookMyShow.dtos.SignUpResponseDto;
import com.swapnil.BookMyShow.models.User;
import com.swapnil.BookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpResponseDto singUp(SignUpRequestDto signUpRequestDto){
        SignUpResponseDto response = new SignUpResponseDto();
        User user;
        try {
            user = userService.singUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setUserId(user.getId());

        }catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
