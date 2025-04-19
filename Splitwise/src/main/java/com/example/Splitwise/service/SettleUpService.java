package com.example.Splitwise.service;

import com.example.Splitwise.model.Expense;
import com.example.Splitwise.model.ExpenseUser;
import com.example.Splitwise.model.Group;
import com.example.Splitwise.model.User;
import com.example.Splitwise.repository.ExpenseRepository;
import com.example.Splitwise.repository.ExpenseUserRepository;
import com.example.Splitwise.repository.GroupRepository;
import com.example.Splitwise.repository.UserRepository;
import com.example.Splitwise.strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpService(GroupRepository groupRepository, ExpenseRepository expenseRepository, UserRepository userRepository, ExpenseUserRepository expenseUserRepository) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;

    }
    public List<Expense> settleUpUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
            throw new RuntimeException();
        }

        User user = optionalUser.get();
        List<ExpenseUser> expenseUsers = expenseUserRepository.findByUser(user);

        Set<Expense> expenses = new HashSet<>();
        for(ExpenseUser expenseUser: expenseUsers) {
            expenses.add(expenseUser.getExpense());
        }
        List<Expense> calculatedExpenses = settleUpStrategy.settleUp(expenses.stream().toList());
        List<Expense> expensesToReturn = new ArrayList<>();

        for(Expense expense: calculatedExpenses) {
            for(ExpenseUser expenseUser: expense.getExpenseUsers()) {
                if(expenseUser.getUser().equals(user)) {
                    expensesToReturn.add(expense);
                    break;
                }
            }
        }
        return expensesToReturn;
    }

    public List<Expense> settleUpGroup(Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if(optionalGroup.isEmpty()) {
            throw new RuntimeException();
        }

        Group group = optionalGroup.get();
        List<Expense> expenses = expenseRepository.findAllByGroup(group);
        return settleUpStrategy.settleUp(expenses);
    }
}
