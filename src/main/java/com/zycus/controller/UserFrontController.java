package com.zycus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zycus.customExceptions.NoRecordsFoundException;

@Controller
public class UserFrontController {
	@Autowired
	private ModelAndView modelAndView;

	@RequestMapping("/user/ViewSurveysShared")
	public ModelAndView userSharedSurveys() {
		modelAndView.setViewName("/user/UserPendingSurveys");
		return modelAndView;

	}

	@RequestMapping("/user/respondToSurvey")
	public ModelAndView respondToSurvey(@RequestParam int surveyId) {
		modelAndView.setViewName("/user/GiveResponse");
		modelAndView.getModel().put("surveyId", surveyId);
		return modelAndView;

	}

	@RequestMapping("/user/viewCompletedSurveys")
	public ModelAndView userCompletedSurveys() {
		modelAndView.setViewName("/user/UserCompletedSurveys");
		return modelAndView;
	}

	@RequestMapping("/user/viewResponse") // for sharing survey
	public ModelAndView viewResponse(@RequestParam int surveyId) {
		// model.addAttribute("surveyId", surveyId);
		modelAndView.setViewName("/user/ViewResponse");
		return modelAndView;

	}

	@RequestMapping("/user/userHome") // for redirecting to user home page
	public ModelAndView userHome() {
		modelAndView.setViewName("/user/userHome");
		return modelAndView;
	}

	@RequestMapping("/user/viewCompleteSurvey") // for going to all questions in a surveys created by admin page
	public ModelAndView viewCompleteSurveys(@RequestParam int surveyId) {
		modelAndView.setViewName("/user/view_survey_questions");
		System.out.println("Error : " + surveyId);
		if (surveyId == 0) {
			throw new NoRecordsFoundException("Survey Id unavailable.", Integer.class);
		}
		modelAndView.getModel().put("surveyId", surveyId);
		return modelAndView;

	}

}
