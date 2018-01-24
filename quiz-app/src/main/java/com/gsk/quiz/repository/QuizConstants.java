package com.gsk.quiz.repository;

public class QuizConstants {

	public String REDIRECT_ADMIN_DASHBOARD = "redirect:/admin/admin-dashboard";
	public String REDIRECT_USER_DASHBOARD = "redirect:/user/user-dashboard";
	public String REDIRECT_USER_CONGRATS = "redirect:/user/user-congrats";
	public String REDIRECT_HOME = "redirect:/home";
	public String ERROR_PAGE = "error";
	
	public enum USER_ROLE {
		
		ROLE_ADMIN, ROLE_USER
		
	}
}
