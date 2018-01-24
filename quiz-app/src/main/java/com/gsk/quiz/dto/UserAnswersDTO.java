package com.gsk.quiz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gsk.quiz.model.UserAnswers;

public class UserAnswersDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<UserAnswers> userAnswers = new ArrayList<>();

	public List<UserAnswers> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswers> userAnswers) {
		this.userAnswers = userAnswers;
	}

}
