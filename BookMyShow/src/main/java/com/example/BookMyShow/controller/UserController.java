package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.SignUpRequestDto;
import com.example.BookMyShow.dto.SignUpResponseDto;
import com.example.BookMyShow.models.ResponseStatus;
import com.example.BookMyShow.models.User;
import com.example.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmailId(), signUpRequestDto.getPassword());
            signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            signUpResponseDto.setUserId(user.getId());
        } catch (Exception e) {
            signUpResponseDto.setResponseStatus(ResponseStatus.FAILED);
            signUpResponseDto.setUserId(-1L);
        }
        return signUpResponseDto;
    }
}
