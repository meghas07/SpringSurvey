package com.zycus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserFrontController {

	@RequestMapping("/user/ViewSurveysShared")
	public String userSharedSurveys() {
		return "UserPendingSurveys";
	}

	@RequestMapping("/user/respondToSurvey")
	public String respondToSurvey(@RequestParam int surveyId, Model model) {
		model.addAttribute("surveyId", surveyId);
		return "GiveResponse";
	}

	@RequestMapping("/user/viewCompletedSurveys")
	public String userCompletedSurveys() {
		return "UserCompletedSurveys";
	}

	@RequestMapping("/user/viewResponse") // for sharing survey
	public String viewResponse(@RequestParam int surveyId, Model model) {
		model.addAttribute("surveyId", surveyId);
		return "ViewResponse";
	}

}
