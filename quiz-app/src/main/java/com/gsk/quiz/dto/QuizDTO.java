package com.gsk.quiz.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class QuizDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String quizId;
	private String quizName;
	private MultipartFile quizCsvFIle;
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public MultipartFile getQuizCsvFIle() {
		return quizCsvFIle;
	}
	public void setQuizCsvFIle(MultipartFile quizCsvFIle) {
		this.quizCsvFIle = quizCsvFIle;
	}
	
	

}
