package com.gsk.quiz.service;

import java.io.File;

import com.gsk.quiz.dto.QuizDTO;
import com.gsk.quiz.model.Quiz;

public interface QuizService {
    void save(QuizDTO quizDTO, File file);

    Quiz findByEmail(String email);
}
