package com.gsk.quiz.service;

import com.gsk.quiz.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}
