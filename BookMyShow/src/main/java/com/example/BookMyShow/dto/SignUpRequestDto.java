package com.example.BookMyShow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String emailId;
    private String password;
}
