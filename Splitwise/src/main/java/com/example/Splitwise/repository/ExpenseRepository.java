package com.example.Splitwise.repository;

import com.example.Splitwise.model.Expense;
import com.example.Splitwise.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
