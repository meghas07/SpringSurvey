package com.zycus.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.entity.Question;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.myenums.Status;
import com.zycus.repository.CrudRepository;
import com.zycus.repository.QuestionRepository;
import com.zycus.repository.SurveyRepository;
import com.zycus.repository.UserRepository;
import com.zycus.service.SurveyService;
import com.zycus.service.UserService;
import com.zycus.service.Validator;

@RestController
public class AdministratorRestController {

	@Autowired
	private UserService userService;
	@Autowired
	private CrudRepository repo;
	@Autowired
	private QuestionRepository qrepo;
	@Autowired
	private SurveyRepository surveyRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SurveyService surveyService;
	@Autowired
	private Validator validator;

	// This is for checking login credentials of user
	@RequestMapping(value = "rest/user/login", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public @ResponseBody String userLogin(@RequestBody User user, HttpServletRequest request) {

		String role = userService.login(user);
		if (role != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", user.getId());
			return role;
		} else
			return "fail";

	}

	// This is for creating survey(only admin access to create survey)

	@RequestMapping(value = "rest/admin/addSurvey", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public @ResponseBody String createSurvey(@RequestBody Survey survey, HttpServletRequest request) {
		String message = surveyService.checkSurveyForm(survey);
		if (message == "success") {

			surveyService.addSurvey(survey);
			HttpSession session = request.getSession();
			session.setAttribute("surveyId", survey.getSurveyId());
			return message;
		} else
			return message;
	}

	// For admin to view all the survey he has created
	@RequestMapping(value = "rest/admin/viewAllSurveysCreated", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Survey> getAllSurveysCreated(@RequestParam int adminId) {
		List<Survey> listOfSurveys = surveyRepo.showAllSurveyByAdmin(adminId);
		System.out.println(listOfSurveys.size());
		return listOfSurveys;
	}

	// To view the created and completed survey
	@RequestMapping(value = "rest/admin/viewCompleteSurvey", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Question> getCompleteSurvey(@RequestParam int surveyId) {
		List<Question> listOfQuestions = qrepo.showAllQuestionsInSurvey(surveyId);
		return listOfQuestions;
	}

	// to change survey status from
	@RequestMapping(value = "rest/admin/changeSurveyStatus", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Status changeStatusOfSurvey(@RequestParam int surveyId) throws Exception {

		Survey survey = (Survey) repo.fetchById(Survey.class, surveyId);
		System.out.println(survey);
		if (survey.getStatus().equals(Status.INACTIVE))
			survey.setStatus(Status.ACTIVE);
		else if (survey.getStatus().equals(Status.ACTIVE))
			survey.setStatus(Status.INACTIVE);

		repo.update(survey);
		return survey.getStatus();

	}

	// To logout
	@RequestMapping(value = "rest/user/logout")
	public @ResponseBody String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userid");
		return "Logged out Successfully";

	}

	// For admin to view all users
	@RequestMapping(value = "rest/admin/viewAllUsers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<User> getAllUsers(@RequestParam int surveyId) {
		List<User> listOfUsers = userRepo.showAllUsersByRole("user");
		System.out.println(listOfUsers.size());
		return listOfUsers;
	}

	// To share surveys shared with user
	@RequestMapping(value = "rest/admin/storeSharedSurveyDetails", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String storeSharedSurvey(@RequestBody Set<SharedSurveysWithUser> sharedSurveyWithUsers) {
		return surveyService.shareSurvey(sharedSurveyWithUsers);
	}

	// To delete surveys
	@RequestMapping(value = "rest/admin/deleteSurvey", method = RequestMethod.GET)
	public @ResponseBody void deleteSurvey(@RequestParam int surveyId) {
		surveyService.deletSurveyRecord(surveyId);
	}

}
