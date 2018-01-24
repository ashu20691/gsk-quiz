package com.gsk.quiz.service;

import com.gsk.quiz.dto.UserAnswersDTO;
import com.gsk.quiz.model.QuizResult;

public interface QuizResultService {
    void save(UserAnswersDTO userAnaswersDTO);

    QuizResult findByEmail(String email);
}
