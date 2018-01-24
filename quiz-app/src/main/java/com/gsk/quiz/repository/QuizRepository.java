package com.gsk.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsk.quiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	
}
