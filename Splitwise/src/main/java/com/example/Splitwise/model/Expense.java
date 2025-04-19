package com.example.Splitwise.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Expense extends BaseModel {
    private Long amount;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Group group;
    @OneToMany (mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;
}
