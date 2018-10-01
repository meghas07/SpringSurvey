package com.zycus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zycus.customExceptions.NoRecordsFoundException;

@Controller
public class AdministratorFrontController {
	@Autowired
	private ModelAndView modelAndView;

	@RequestMapping("/home")
	public String displayMessage() {
		return "index";
	}

	@RequestMapping("/admin/AdminHome")
	public ModelAndView returnHome() {
		modelAndView.setViewName("/admin/AdministratorHome");
		return modelAndView;
	}

	@RequestMapping("/admin/createSurvey") // for creating A survey
	public ModelAndView createNewSurveys() {
		modelAndView.setViewName("/admin/createSurvey");
		return modelAndView;
	}

	@RequestMapping("/admin/viewAllSurvey") // for going to all surveys created by admin page
	public ModelAndView viewAllTheSurveys() {
		modelAndView.setViewName("/admin/viewAllSurveys");
		return modelAndView;
	}

	@RequestMapping("/admin/viewCompleteSurvey") // for going to all questions in a surveys created by admin page
	public ModelAndView viewCompleteSurveys(@RequestParam int surveyId) {
		modelAndView.setViewName("/admin/viewSurveyDetails");
		System.out.println("Error : " + surveyId);
		if (surveyId == 0) {
			throw new NoRecordsFoundException("Survey Id unavailable.", Integer.class);
		}
		modelAndView.getModel().put("surveyId", surveyId);
		return modelAndView;

	}

	@RequestMapping("/admin/shareSurvey") // for sharing survey
	public ModelAndView shareSurveys(@RequestParam int surveyId) {
		modelAndView.setViewName("/admin/shareSurveyWithUsers");
		modelAndView.getModel().put("surveyId", surveyId);
		return modelAndView;
	}

}
