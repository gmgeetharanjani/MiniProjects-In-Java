package com.example.Splitwise.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "splitWiseUser")
@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private int phoneNumber;
    private String password;
}
