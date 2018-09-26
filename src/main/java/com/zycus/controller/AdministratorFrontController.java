package com.zycus.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class AdministratorFrontController {

	@RequestMapping("/home")
	public String displayMessage() {

		return "index";

	}

	@RequestMapping("/admin/Administratorhome") // for redirecting to admin home page
	public String administratorHome() {
		return "AdministratorHome";
	}

	@RequestMapping("/admin/createSurvey") // for creating A survey
	public String createNewSurveys() {
		return "createSurvey";
	}

	@RequestMapping("/admin/viewAllSurvey") // for going to all surveys created by admin page
	public String viewAllTheSurveys() {
		return "viewAllSurveys";
	}

	@RequestMapping("/admin/viewCompleteSurvey") // for going to all questions in a surveys created by admin page
	public String viewCompleteSurveys(@RequestParam int surveyId, Map model) {
		model.put("surveyId", surveyId);
		return "viewSurveyDetails";
	}

	@RequestMapping("/user/userHome") // for redirecting to user home page
	public String userHome() {
		return "userHome";
	}

	@RequestMapping("/admin/shareSurvey") // for sharing survey
	public String shareSurveys(@RequestParam int surveyId, Map model) {
		model.put("surveyId", surveyId);
		return "shareSurveyWithUsers";
	}

}
