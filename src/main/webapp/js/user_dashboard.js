/**
 * 
 */

$(document).ready(function() {

	$("#view_surveys_shared").click(function() {
		window.location = "/SurveyProjectMVC/user/ViewSurveysShared";
	});

	$("#view_answered_surveys").click(function() {
		window.location = "/SurveyProjectMVC/user/viewCompletedSurveys";
	});

}); // end of dom functo
