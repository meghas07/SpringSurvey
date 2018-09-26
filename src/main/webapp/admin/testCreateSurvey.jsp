<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	int adminid = (Integer) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript">
	$(document).ready(	function(){
						var admin_id =<%=adminid%>	;
						var counter = 2;
						var surveyId = $("#survey_id").val();
						var question;
						var i;

						var surveyObj;

						/* start of function */
							/* function 1 */ $("#addButton").click(function() {

								if (counter > 10) {
									alert("Maximum 10 questions allowed");
									return false;
								}

								var newTextBoxDiv = $(document.createElement('div')).attr("id",'TextBoxDiv'	+ counter);
								newTextBoxDiv.after().html('<label>Question #'
														+ counter
														+ ' : </label>'
														+ '<textarea rows="2" cols="100" ' 
														+ counter +'" id="textbox'
														+ counter + '" value="" placeholder = "Enter text here..."></textarea>');

								newTextBoxDiv.appendTo("#TextBoxesGroup");
								counter++;
							}); 
			/*  end of function 1*/

			/* function 2 */	$("#removeButton").click(function() {
				if (counter == 1) {
					alert("No more textbox to remove");
					return false;
				}

				counter--;

				$("#TextBoxDiv" + counter).remove();

			});
			/*  end of function 1*/

						$("#create_survey_form").submit(function(event) {
											event.preventDefault();
											surveyObj = {
												"surveyId" : $("#survey_id")
														.val(),
												"surveyName" : $("#survey_name")
														.val(),
												"user" : {
													"id" : admin_id
												},
											"setOfQuestionsInSurvey":[]
											
											}
											
											for ( i = 0; i < counter - 1; i++) {
												 question = {
																"questionDescription" : $('#textbox'+ (i + 1)).val(),
																	 "survey":{
																			"surveyId":$("#survey_id")
																			.val()
																		} 
																	}

										surveyObj.setOfQuestionsInSurvey.push(question);
											}
												
								

											/* end of function */

											console.log(surveyObj);
											/*converting survey object to json format */
											var surveyObjAsJSON = JSON
													.stringify(surveyObj);

											$
													.ajax({
														url : "/SurveyProjectMVC/rest/admin/addSurvey",
														type : "POST",
														data : surveyObjAsJSON,
														contentType : "application/json",
														success : function(
																response,
																status) {
															if (response == "success") {

																window.location = "/SurveyProjectMVC/admin/createSurveyQuestionnaire";
															}
															else 
																alert(response);
														}
													});
										});
						//logout function call
						$("#logout").click(function() {
							$.ajax({
								url : "../rest/user/logout",
								success : function(response, status) {
									alert(response);
									window.location = "/SurveyProjectMVC/home";
								}

							});

						});
					
					});
</script>
</head>
<body>
<form id="create_survey_form">
						<div class="form-group">
							<label for="surveyid" class="sr-only">Enter survey Id:</label> <input
								type="text" name="userid" id="survey_id" class="form-control"
								placeholder="Survey ID">
						</div>
						<div class="form-group">
							<label for="survey_name" class="sr-only">Password</label> <input
								type="text" name="key" id="survey_name" class="form-control"
								placeholder="Survey Name">
						</div>
						<input type="submit" id="btn-login"
							class="btn btn-custom btn-lg btn-block" value="Submit">
							<div id='TextBoxesGroup'>
			<div id="TextBoxDiv1">
				<label>Question #1 : </label>
				<!-- <input type='text' id='textbox1'> -->
				<textarea rows="2" cols="100" id='textbox1' placeholder = "Enter text here...">
</textarea>
			</div>
		</div>
		<input type='button' value='Add Question' id='addButton'> <input
			type='button' value='Remove Question' id='removeButton'>
		<!-- <input type='button' value='Get TextBox Value' id='getButtonValue'> -->
		<input type="submit" value="Create survey" />
					</form>
</body>
</html>