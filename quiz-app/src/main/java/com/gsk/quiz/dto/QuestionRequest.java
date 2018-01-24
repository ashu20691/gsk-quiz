package com.gsk.quiz.dto;

import java.util.ArrayList;
import java.util.List;

import com.gsk.quiz.model.UserAnswers;

public class QuestionRequest {

	List<UserAnswers> userAnswers = new ArrayList<>();

	public List<UserAnswers> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswers> userAnswers) {
		this.userAnswers = userAnswers;
	}

	
	
}
