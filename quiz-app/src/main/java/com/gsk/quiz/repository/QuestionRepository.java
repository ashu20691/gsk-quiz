package com.gsk.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsk.quiz.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

		Question findByQuesId(Long quesid);
}
