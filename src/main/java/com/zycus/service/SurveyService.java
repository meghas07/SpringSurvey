package com.zycus.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.customExceptions.CouldNotPerformOperationException;
import com.zycus.customExceptions.IncompleteDetailsException;
import com.zycus.customExceptions.InvalidDetailsException;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.myenums.ResponseStatus;
import com.zycus.myenums.Status;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.ResponseRepository;
import com.zycus.repository.SurveyRepository;

@Service
public class SurveyService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CrudRepository repository;
	@Autowired
	private Validator validator;
	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private ResponseRepository responseRepository;

	// 1. Add a survey to the database

	@Transactional
	public boolean addSurvey(Survey survey) {

		if (validator.checkSurveyForm(survey) == true) {
			survey.setStatus(Status.INACTIVE);
			repository.addRecord(survey);
			return true;

		}
		return false;

	}

	// 2.View All the surveys
	public List<Survey> viewAllSurveysByAdminId(int adminId) {
		return surveyRepository.showAllSurveyByAdmin(adminId);
	}

	// 3.Share survey with users.
	@SuppressWarnings("unchecked")
	@Transactional
	public String shareSurvey(Set<SharedSurveysWithUser> sharedSurvey) {
		try {
			for (SharedSurveysWithUser shared : sharedSurvey) {
				int flag = surveyRepository.checkIfSurveyAlreadyShared(shared);
				if (flag == -1)
					return "could not share please try again";
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

	// 4.Delete surveys from database
	@SuppressWarnings("unchecked")
	@Transactional
	public void deletSurveyRecord(int surveyId) {
		surveyRepository.deleteSurveyShared(surveyId);
		responseRepository.deleteResponsesForSurvey(surveyId);
		repository.delete(Survey.class, surveyId);

	}

	@SuppressWarnings("unchecked")
	public Survey fetchSurveyById(int surveyId) {
		return (Survey) repository.fetchById(Survey.class, surveyId);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Status changeSurveyStatus(int surveyId) {

		Survey survey = (Survey) repository.fetchById(Survey.class, surveyId);
		if (survey.getStatus().equals(Status.INACTIVE))
			survey.setStatus(Status.ACTIVE);
		else if (survey.getStatus().equals(Status.ACTIVE))
			survey.setStatus(Status.INACTIVE);
		repository.update(survey);
		return survey.getStatus();

	}

}
