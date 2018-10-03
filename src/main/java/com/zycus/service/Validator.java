package com.zycus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.customExceptions.IncompleteDetailsException;
import com.zycus.customExceptions.InvalidDetailsException;
import com.zycus.entity.Question;
import com.zycus.entity.Survey;
import com.zycus.repository.CrudRepository;

@Service
public class Validator {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CrudRepository crudRepo;

	@SuppressWarnings("unchecked")
	public boolean checkSurveyForm(Survey survey) {

		if (survey == null || survey.getSurveyId() == 0 || survey.getSurveyName() == null
				|| survey.getSurveyName() == "") {
			System.out.println("gfkjvbfdjv");
			throw new IncompleteDetailsException("Please fill in all details", survey);
		}

		if (crudRepo.fetchById(Survey.class, survey.getSurveyId()) != null)
			throw new InvalidDetailsException("This id is already in use.Please try another", survey);

		for (Question question : survey.getSetOfQuestionsInSurvey())
			if (question.getQuestionDescription() == null || question.getQuestionDescription() == "")
				throw new IncompleteDetailsException("Please fill in all details", survey);
		return true;

	}

}
