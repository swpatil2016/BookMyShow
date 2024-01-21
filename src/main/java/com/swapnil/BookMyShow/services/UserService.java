package com.swapnil.BookMyShow.services;

import com.swapnil.BookMyShow.Repositories.UserRepository;
import com.swapnil.BookMyShow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User singUp(String email, String password){
        // Check if user is already there or not



        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user); // save user in DB

        return savedUser;
    }
}
