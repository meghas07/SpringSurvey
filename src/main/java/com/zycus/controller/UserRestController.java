package com.zycus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.dto.QuestionAnswer;
import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.Survey;
import com.zycus.service.QuestionService;
import com.zycus.service.ResponseService;
import com.zycus.service.UserSurveyService;

@RestController
public class UserRestController {
	@Autowired
	private UserSurveyService userSurveyService;

	@Autowired
	private QuestionService questionService;
	@Autowired
	private ResponseService responseService;

	// To fetch surveys that are pending
	@RequestMapping(value = "rest/user/viewSharedSurveysList", method = RequestMethod.GET, produces = "application/json")
	public List<Survey> fetchAllPendingSurveys(@RequestParam int userId) {
		return userSurveyService.showPendingSurveys(userId);

	}

	// To retrieve question for given survey
	@RequestMapping(value = "rest/user/giveResponse", method = RequestMethod.GET, produces = "application/json")
	public List<Question> giveResponseToSurvey(@RequestParam int surveyId) {

		return questionService.fetchAllQuestions(surveyId);
	}

	// This is for storing responses in database
	@RequestMapping(value = "rest/user/addResponse", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String addResponseForSurvey(@RequestBody List<Response> listOfUserResponse) {
		return responseService.addResponseByUser(listOfUserResponse);
	}

	// To fetch surveys that are pending
	@RequestMapping(value = "rest/user/viewCompletedSurveys", method = RequestMethod.GET, produces = "application/json")
	public List<Survey> fetchAllCompletedSurveys(@RequestParam int userId) {
		return userSurveyService.showCompletedSurveys(userId);

	}

	// To fetch response submitted
	@RequestMapping(value = "rest/user/viewCompleteResponse", method = RequestMethod.GET, produces = "application/json")
	public List<QuestionAnswer> fetchCompleteResponse(@RequestParam int surveyId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userid");
		return userSurveyService.showCompletedResponse(surveyId, userId);
	}

	// To view the created and completed survey
	@RequestMapping(value = "rest/user/viewCompleteSurvey", method = RequestMethod.GET, produces = "application/json")
	public List<Question> getCompleteSurvey(@RequestParam int surveyId) {
		return questionService.fetchAllQuestions(surveyId);
	}

}
