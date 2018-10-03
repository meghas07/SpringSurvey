package com.zycus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.Question;
import com.zycus.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> fetchAllQuestions(int surveyId) {

		return questionRepository.showAllQuestionsInSurvey(surveyId);
	}

}
