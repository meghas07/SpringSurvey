package com.zycus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.dto.QuestionAnswer;
import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.myenums.ResponseStatus;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.QuestionRepository;
import com.zycus.repository.ResponseRepository;
import com.zycus.repository.SurveyRepository;

@Service
public class UserSurveyService {

	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private CrudRepository<Survey> crudRepository;
	@Autowired
	private ResponseRepository responseRepository;
	@Autowired
	private QuestionRepository questionRepository;

	// Show Surveys that have not been answered
	public List<Survey> showPendingSurveys(Object userId) {
		System.out.println(userId + "survey service");
		List<SharedSurveysWithUser> listOfSharedSurveys = surveyRepository.showAllSurveyForUser(userId);
		List<Survey> listOfSurveys = new ArrayList<Survey>();

		for (SharedSurveysWithUser sharedSurvey : listOfSharedSurveys) {
			try {
				if (sharedSurvey.getResponseStatus().equals(ResponseStatus.PENDING)) {

					listOfSurveys.add(crudRepository.fetchById(Survey.class, sharedSurvey.getSurvey().getSurveyId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return listOfSurveys;

	}

	// Show Surveys that have been answered
	public List<Survey> showCompletedSurveys(Object userId) {
		List<SharedSurveysWithUser> listOfSharedSurveys = surveyRepository.showAllSurveyForUser(userId);
		List<Survey> listOfSurveys = new ArrayList<Survey>();

		for (SharedSurveysWithUser sharedSurvey : listOfSharedSurveys) {
			try {
				if (sharedSurvey.getResponseStatus().equals(ResponseStatus.COMPLETED)) {
					listOfSurveys.add(crudRepository.fetchById(Survey.class, sharedSurvey.getSurvey().getSurveyId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return listOfSurveys;

	}

	// Show Response given for a particular survey
	public List<QuestionAnswer> showCompletedResponse(int surveyId, int userId) {
		List<Response> listOfResponses = responseRepository.listOfResponses(surveyId, userId);
		List<Question> listOfQuestions = questionRepository.showAllQuestionsInSurvey(surveyId);
		List<QuestionAnswer> questionAndAnswer = new ArrayList<QuestionAnswer>();
		for (int i = 0; i < listOfQuestions.size(); i++) {
			QuestionAnswer questionWithAnswer = new QuestionAnswer();
			questionWithAnswer.setQuestion(listOfQuestions.get(i));
			questionWithAnswer.setResponse(listOfResponses.get(i));
			questionAndAnswer.add(questionWithAnswer);
		}
		return questionAndAnswer;
	}

}
