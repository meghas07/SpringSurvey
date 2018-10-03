package com.zycus.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.customExceptions.InvalidDetailsException;
import com.zycus.entity.Question;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.myenums.Status;
import com.zycus.service.AdministratorService;
import com.zycus.service.QuestionService;
import com.zycus.service.SurveyService;

@RestController
public class AdministratorRestController {
	@Autowired
	private QuestionService questionService;

	@Autowired
	private AdministratorService adminService;
	@Autowired
	private SurveyService surveyService;

	// This is for creating survey(only admin access to create survey)
	@RequestMapping(value = "rest/admin/addSurvey", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public void createSurvey(@RequestBody Survey survey) {

		surveyService.addSurvey(survey);
	}

	// For admin to view all the survey he has created
	@RequestMapping(value = "rest/admin/viewAllSurveysCreated", method = RequestMethod.GET, produces = "application/json")
	public List<Survey> getAllSurveysCreated(@RequestParam int adminId) {
		return surveyService.viewAllSurveysByAdminId(adminId);
	}

	// To view the created and completed survey
	@RequestMapping(value = "rest/admin/viewCompleteSurvey", method = RequestMethod.GET, produces = "application/json")
	public List<Question> getCompleteSurvey(@RequestParam int surveyId) {
		List<Question> listOfQuestions = questionService.fetchAllQuestions(surveyId);
		return listOfQuestions;
	}

	// to change survey status from
	@RequestMapping(value = "rest/admin/changeSurveyStatus", method = RequestMethod.GET, produces = "application/json")
	public Status changeStatusOfSurvey(@RequestParam int surveyId) throws Exception {

		return surveyService.changeSurveyStatus(surveyId);
	}

	// For admin to view all users
	@RequestMapping(value = "rest/admin/viewAllUsers", method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUsers() {
		List<User> listOfUsers = adminService.fetchAllUsers();
		System.out.println(listOfUsers.size());
		return listOfUsers;
	}

	// To share surveys shared with user
	@RequestMapping(value = "rest/admin/storeSharedSurveyDetails", method = RequestMethod.POST, produces = "text/plain")
	public String storeSharedSurvey(@RequestBody Set<SharedSurveysWithUser> sharedSurveyWithUsers) {
		return surveyService.shareSurvey(sharedSurveyWithUsers);
	}

	// To delete surveys
	@RequestMapping(value = "rest/admin/deleteSurvey", method = RequestMethod.GET)
	public void deleteSurvey(@RequestParam int surveyId) {
		surveyService.deletSurveyRecord(surveyId);
	}

}
