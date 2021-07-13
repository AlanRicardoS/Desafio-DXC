package com.example.microsservico.repository;

import com.example.microsservico.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alan Ricardo
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
