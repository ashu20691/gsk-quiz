package com.gsk.quiz.service;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsk.quiz.dto.QuizDTO;
import com.gsk.quiz.model.Answer;
import com.gsk.quiz.model.Question;
import com.gsk.quiz.model.Quiz;
import com.gsk.quiz.repository.QuizRepository;
import com.gsk.quiz.util.ParseCSVFile;


@Service
public class QuizServiceImpl implements QuizService {
	protected static Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public void save(QuizDTO quizDTO, File file) {
    	
    	Map<Question, List<Answer>> quesAnsMap = ParseCSVFile.parseCSVFile(file);
    	
    	if(quesAnsMap.size() > 0 ) {
    		ModelMapper modelMapper = new ModelMapper();
        	Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        	
        	//quizRepository.save(quiz);
        	
        	Set<Map.Entry<Question, List<Answer>>> entrySet = quesAnsMap.entrySet();
        	Set<Question> quesSet = new HashSet<>();
        	
        	for (Entry<Question, List<Answer>> entry : entrySet) {

        		Question ques = (Question) entry.getKey();
    			List<Answer> ansSet = (List<Answer>) entry.getValue();
        		
        		ques.setAnswers(ansSet);
        		ques.setQuiz(quiz);
        		
        		quesSet.add(ques);
        		
        		//questionRepository.save(ques);
        		
        	}
    		quiz.setQuestions(quesSet);
    		quizRepository.save(quiz);


        	logger.info("saved quiz to database" + quiz);
    		
    	}else {
    		
    		logger.info("csv file is either empty or in incorrect format");
    		
    	}
    	
    }

	@Override
	public Quiz findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
