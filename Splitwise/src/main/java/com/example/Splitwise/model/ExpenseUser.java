package com.example.Splitwise.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExpenseUser extends BaseModel {
    private Long amount;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
}
