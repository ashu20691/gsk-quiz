package com.gsk.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsk.quiz.model.QuizResult;

public interface QuizResultRepository extends JpaRepository<QuizResult,Long>{

}
