package com.swapnil.BookMyShow;

import com.swapnil.BookMyShow.controllers.UserController;
import com.swapnil.BookMyShow.dtos.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDto signUpRequestDto= new SignUpRequestDto();
		signUpRequestDto.setEmail("shubham@gmail.com");
		signUpRequestDto.setPassword("password");

		userController.singUp(signUpRequestDto);
	}
}
