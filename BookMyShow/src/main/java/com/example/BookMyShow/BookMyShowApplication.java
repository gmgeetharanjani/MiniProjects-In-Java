package com.example.BookMyShow;

import com.example.BookMyShow.controller.UserController;
import com.example.BookMyShow.dto.SignUpRequestDto;
import com.example.BookMyShow.dto.SignUpResponseDto;
import com.example.BookMyShow.models.BaseModel;
import com.example.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
    @Autowired
    private UserController userController;
    public static void main(String[] args) {
		BaseModel baseModel = new BaseModel();
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("Geetha Ranjani");
        signUpRequestDto.setEmailId("gmgeetharanjani@gmail.com");
        signUpRequestDto.setPassword("password123");
        SignUpResponseDto signUpResponseDto = userController.signUp(signUpRequestDto);
    }
}
