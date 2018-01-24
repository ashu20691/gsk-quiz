package com.gsk.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsk.quiz.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(Long userid);
}
