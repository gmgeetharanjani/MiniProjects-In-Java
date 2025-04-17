package com.example.BookMyShow.repository;

import com.example.BookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long userId);
    // Custom query to find a user by email
    Optional<User> findByEmail(String email);

    @Override
    User save(User user);
}

/* To create any Repository follow 2 steps:
    1. Make the Repository an interface
    2. Extend the JpaRepository interface with the entity class and the type of the primary key
 */
