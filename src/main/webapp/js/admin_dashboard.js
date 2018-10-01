/**
 * 
 */

$(document).ready(function() {

	 //create survey function
	 $("#create_survey").click(function() {
	  window.location = "/SurveyProjectMVC/admin/createSurvey";
	 });

	 //view all surveys function call
	 $("#view_all_surveys").click(function() {

	  window.location = "/SurveyProjectMVC/admin/viewAllSurvey";
	 });
	 
	}); //end of dom functon