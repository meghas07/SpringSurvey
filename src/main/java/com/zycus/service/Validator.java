package com.zycus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.Question;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.UserRepository;

@Service
public class Validator {
	@Autowired
	private UserRepository repo;
	@Autowired
	private CrudRepository crudRepo;

	public User checkLoginCredentials(User user) {
		return repo.checkUserLoginCredentials(user);

	}

	@SuppressWarnings("unchecked")
	public String checkSurveyForm(Survey survey) {
		if (survey.getSurveyId() == 0 || survey.getSurveyName() == null || survey.getSurveyName() == "")
			return "Please Enter All details";
		else {

			for (Question question : survey.getSetOfQuestionsInSurvey())
				if (question.getQuestionDescription() == null || question.getQuestionDescription() == "")
					return "Please do not leave the question field blank";
				else
					try {
						if (crudRepo.fetchById(Survey.class, survey.getSurveyId()) != null)
							return "This id is is already in use.Pleaase try another.";
					} catch (Exception e) {
						e.printStackTrace();
					}

			return "success";
		}

	}

}
