package com.gsk.quiz.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsk.quiz.dto.UserAnswersDTO;
import com.gsk.quiz.model.Question;
import com.gsk.quiz.model.QuizResult;
import com.gsk.quiz.model.User;
import com.gsk.quiz.model.UserAnswers;
import com.gsk.quiz.repository.QuestionRepository;
import com.gsk.quiz.repository.QuizResultRepository;
import com.gsk.quiz.repository.UserRepository;


@Service
public class QuizResultServiceImpl implements QuizResultService {
	protected static Logger logger = LoggerFactory.getLogger(QuizResultServiceImpl.class);

    @Autowired
    private QuizResultRepository quizResultRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
	@Override
	public void save(UserAnswersDTO userAnswers) {
		
		Question ques;
		Long userid;
		
		Integer correctAnswerCounter=0;
		Map<Long, Integer> userCorrectAnsMap = new HashMap<>();
		
		for (UserAnswers userAns : userAnswers.getUserAnswers()) {
			userid = userAns.getUserid();
			
			ques = questionRepository.findByQuesId(userAns.getQuesid());
			if(ques.getCorrectAnswer().getAnsId() == userAns.getAnsid()) {
				userCorrectAnsMap.put(userid, ++correctAnswerCounter);
			}
			
		}
		
		Set<QuizResult> quizResultSet =  new HashSet<>();
		QuizResult quizResult;
		User user;
		for (Long key : userCorrectAnsMap.keySet()) {
			quizResult = new QuizResult();
			user = userRepository.findByUserId(key);
			quizResult.setResult(userCorrectAnsMap.get(key)+"");
			quizResult.setUser(user);
			quizResultSet.add(quizResult);
		}
		
		// save to database
		quizResultRepository.save(quizResultSet);
		logger.info("quiz result saved to database");
		
	}
	@Override
	public QuizResult findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
