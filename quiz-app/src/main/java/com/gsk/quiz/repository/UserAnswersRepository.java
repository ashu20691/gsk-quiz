package com.gsk.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsk.quiz.model.UserAnswers;

public interface UserAnswersRepository extends JpaRepository<UserAnswers, Long> {
	
		
}
