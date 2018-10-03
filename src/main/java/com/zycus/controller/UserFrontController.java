package com.zycus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zycus.customExceptions.NoRecordsFoundException;

@Controller
@RequestMapping("/user")
public class UserFrontController {
	@Autowired
	private ModelAndView modelAndView;

	@RequestMapping("/ViewSurveysShared")
	public ModelAndView userSharedSurveys() {
		modelAndView.setViewName("/user/UserPendingSurveys");
		return modelAndView;

	}

	@RequestMapping("/respondToSurvey")
	public ModelAndView respondToSurvey(@RequestParam int surveyId) {
		modelAndView.setViewName("/user/GiveResponse");
		return modelAndView;

	}

	@RequestMapping("/viewCompletedSurveys")
	public ModelAndView userCompletedSurveys() {
		modelAndView.setViewName("/user/UserCompletedSurveys");
		return modelAndView;
	}

	@RequestMapping("/viewResponse") // for sharing survey
	public ModelAndView viewResponse(@RequestParam int surveyId) {

		modelAndView.setViewName("/user/ViewResponse");
		return modelAndView;

	}

	@RequestMapping("/userHome") // for redirecting to user home page
	public ModelAndView userHome() {
		modelAndView.setViewName("/user/userHome");
		return modelAndView;
	}

	@RequestMapping("/viewCompleteSurvey") // for going to all questions in a surveys created by admin page
	public ModelAndView viewCompleteSurveys(@RequestParam int surveyId) {
		modelAndView.setViewName("/user/view_survey_questions");
		System.out.println("Error : " + surveyId);
		if (surveyId == 0) {
			throw new NoRecordsFoundException("Survey Id unavailable.", Integer.class);
		}
		return modelAndView;

	}

}
