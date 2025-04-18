package com.example.Splitwise.repository;

import com.example.Splitwise.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Override
    Optional<Group> findById(Long id);
}
