package com.zycus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.entity.Response;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.myenums.ResponseStatus;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.ResponseRepository;
import com.zycus.repository.SurveyRepository;

@Service
public class ResponseService {

	@Autowired
	private CrudRepository repository;
	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private ResponseRepository responseRepository;

	public String addResponseByUser(List<Response> listOfResponses) {
		boolean flag = false;
		int userId = 0, surveyId = 0;

		SharedSurveysWithUser checkIfAnsweredSurvey = new SharedSurveysWithUser();
		SharedSurveysWithUser sharedSurveysWithUser = new SharedSurveysWithUser();
		try {
			for (Response response : listOfResponses) {

				userId = response.getUser().getId();
				surveyId = response.getSurvey().getSurveyId();
				sharedSurveysWithUser.setSurvey((Survey) repository.fetchById(Survey.class, surveyId));
				sharedSurveysWithUser.setUser((User) repository.fetchById(User.class, userId));
				checkIfAnsweredSurvey = responseRepository.returnAsnweredSurvey(sharedSurveysWithUser);
				if ((checkIfAnsweredSurvey.getResponseStatus()).equals(ResponseStatus.COMPLETED))
					return "fail!";
				repository.addRecord(response);
				flag = true;
			}
			if (flag == true) {

				checkIfAnsweredSurvey.setResponseStatus(ResponseStatus.COMPLETED);
				repository.update(checkIfAnsweredSurvey);
				return "success";

			}
			return "fail";

		} catch (Exception ex) {
			ex.printStackTrace();
			return "fail";
		}

	}

}
