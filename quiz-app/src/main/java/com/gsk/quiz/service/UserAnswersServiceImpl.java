package com.gsk.quiz.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsk.quiz.dto.UserAnswersDTO;
import com.gsk.quiz.model.UserAnswers;
import com.gsk.quiz.repository.UserAnswersRepository;
import com.gsk.quiz.util.MyLoggerFactory;

@Service
public class UserAnswersServiceImpl implements UserAnswersService{
    private static final Logger logger = MyLoggerFactory.getLogger();

	@Autowired
	UserAnswersRepository userAnswerRepository;
	
	@Override
	public void save(UserAnswersDTO userAnswersDTO) {
		
	Set<UserAnswers> userAnswersSet =  new HashSet<UserAnswers>(userAnswersDTO.getUserAnswers());
	
	userAnswerRepository.save(userAnswersSet);
	
	logger.info("saved userAnswers to database");

	}

}
