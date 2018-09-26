package com.zycus.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.myenums.ResponseStatus;
import com.zycus.myenums.Status;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.ResponseRepository;
import com.zycus.repository.SurveyRepository;

@Service
public class SurveyService {

	@Autowired
	private CrudRepository repository;
	@Autowired
	private Validator validator;
	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private ResponseRepository responseRepository;

	public String checkSurveyForm(Survey survey) {
		return validator.checkSurveyForm(survey);
	}

	public void addSurvey(Survey survey) {
		survey.setStatus(Status.INACTIVE);
		repository.addRecord(survey);
	}

	public String shareSurvey(Set<SharedSurveysWithUser> sharedSurvey) {
		System.out.println(sharedSurvey.size());
		try {
			for (SharedSurveysWithUser shared : sharedSurvey) {
				int flag = surveyRepository.checkIfSurveyAlreadyShared(shared);
				if (flag == -1)
					return "could not shar eplease try again";
				else if (flag == 1) {
					System.out.println(shared);
					shared.setId((int) new Date().getTime());
					shared.setResponseStatus(ResponseStatus.PENDING);

					repository.addRecord(shared);
				} else
					return "Survey already shared with user" + shared.getUser().getId();

			}
			return "success";
		} catch (

		Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public void deletSurveyRecord(int surveyId) {
		surveyRepository.deleteSurveyShared(surveyId);
		responseRepository.deleteResponsesForSurvey(surveyId);
		repository.delete(Survey.class, surveyId);

	}

}
