package com.example.Splitwise.command;

import com.example.Splitwise.controller.SettleUpController;
import com.example.Splitwise.dto.SettleUpUserRequestDto;
import com.example.Splitwise.dto.SettleUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SettleUp implements Command {


    private final SettleUpController settleUpController;

    @Autowired
    public SettleUp(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }
    @Override
    public boolean matches(String input) {
        // u1 SettleUp
        List<String> words = List.of(input.split(""));
        return words.get(1).equals("SettleUp");
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDto settleUpUserRequestDto = new SettleUpUserRequestDto();
        settleUpUserRequestDto.setUserId(userId);
        SettleUpUserResponseDto settleUpUserResponseDto = settleUpController.settleUp(settleUpUserRequestDto);
        if (settleUpUserResponseDto != null) {
            System.out.println("Settle Up User Response: " + settleUpUserResponseDto);
        } else {
            System.out.println("No expenses found for user ID: " + userId);
        }
    }
}
