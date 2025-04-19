package com.example.Splitwise.strategy;

import com.example.Splitwise.model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    public List<Expense> settleUp(List<Expense> expenses);
}
