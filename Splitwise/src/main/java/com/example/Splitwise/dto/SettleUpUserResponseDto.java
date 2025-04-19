package com.example.Splitwise.dto;

import com.example.Splitwise.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    List<Expense> expenses;
}
