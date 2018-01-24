package com.gsk.quiz.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
