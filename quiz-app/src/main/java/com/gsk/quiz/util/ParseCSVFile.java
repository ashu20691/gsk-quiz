package com.gsk.quiz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsk.quiz.model.Answer;
import com.gsk.quiz.model.Question;

public class ParseCSVFile {
	
	protected static Logger logger = LoggerFactory.getLogger(ParseCSVFile.class);


	public static Map<Question, List<Answer>> parseCSVFile(File file) {
		
		logger.info("parsing csv file for ques ans ");

		String line = "";	
		String DELIMITER = "\\|";  // delimiter as | 
		Map<Question, List<Answer>> quesAnsMap = new HashMap<>();
		
		List<Answer> ansList = null ;
		
		Question ques = null;
		Answer ans1 = null;
		Answer correctAnswer = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			while (((line = br.readLine()) != null ) && !(line.isEmpty())) {
				ques = new Question();
				ans1 = new Answer();
				ansList = new ArrayList<>();

				//correctAnswer = new Answer();

				// use comma as separator
				String[] quesAnsAry = line.split(DELIMITER);
				
				if(quesAnsAry.length > 1) {
					ques.setText(quesAnsAry[0]);

					for (int i = 1; i < quesAnsAry.length-1; i++) {
						ans1 = new Answer();
						ans1.setText(quesAnsAry[i]);
						ans1.setQuestion(ques);
						
						//assign correct answer
						if(quesAnsAry[i].trim().equals(quesAnsAry[quesAnsAry.length-1].trim())) {
							correctAnswer = ans1;
						}
						ansList.add(ans1);
					}
					
					ques.setAnswers(ansList);
					ques.setCorrectAnswer(correctAnswer);
				}
				
				quesAnsMap.put(ques, ansList);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return quesAnsMap;

	}

}
