package com.zycus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.Question;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private CrudRepository repository;
	@Autowired
	private QuestionRepository questionRepository;

	public <T> void addQuestion(T myObject) {
		repository.addRecord(myObject);
	}

	public List<Question> fetchAllQuestions(int surveyId) {

		return questionRepository.showAllQuestionsInSurvey(surveyId);
	}

}
