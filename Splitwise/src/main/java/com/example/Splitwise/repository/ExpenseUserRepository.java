package com.example.Splitwise.repository;

import com.example.Splitwise.model.ExpenseUser;
import com.example.Splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    List<ExpenseUser> findByUser(User user);
}
