package com.example.Splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Splitwise.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long userId);
}
