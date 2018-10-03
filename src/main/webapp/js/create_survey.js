/**
 * 
 */
$(document)
		.ready(
				function() {
					var surveyId = $("#survey_id").val();
					var surveyObj;
					var question;
					var i;
					var counter = 2;

					/* function 1 */
					$("#addButton")
							.click(
									function() {

										if (counter > 10) {
											alert("Maximum 10 questions allowed");
											return false;
										}

										var newTextBoxDiv = $(
												document.createElement('div'))
												.attr("id",
														'TextBoxDiv' + counter);
										newTextBoxDiv
												.after()
												.html(
														'<label>Question #'
																+ counter
																+ ' : </label>'
																+ '<textarea rows="2" cols="100" '
																+ counter
																+ '" id="textbox'
																+ counter
																+ '" value="" placeholder = "Enter text here..." class="form-control textBoxClass"></textarea>');

										newTextBoxDiv
												.appendTo("#TextBoxesGroup");
										counter++;
									});
					/* end of function 1 */

					/* function 2 */
					$("#removeButton").click(function() {
						if (counter == 1) {
							alert("No more textbox to remove");
							return false;
						}

						counter--;

						$("#TextBoxDiv" + counter).remove();

					});
					/* end of function 2 */

					/* start of function for form details */
					$("#create_survey_form")
							.submit(
									function(event) {
										event.preventDefault();

										
										if ($.isNumeric($("#survey_id").val())) {

											/*
											 * stat of creation of survey object
											 */
											surveyObj = {
												"surveyId" : $("#survey_id")
														.val(),
												"surveyName" : $("#survey_name")
														.val(),
												"user" : {
													"id" : admin_id
												},

												"setOfQuestionsInSurvey" : []
											}
											/*--------- end of survey Object */

											/*
											 * start of adding question array of
											 * questions
											 */
											for (i = 0; i < counter - 1; i++) {
												console.log(counter)
												question = {
													"questionDescription" : $(
															'#textbox'
																	+ (i + 1))
															.val(),
													"survey" : {
														"surveyId" : $(
																"#survey_id")
																.val()
													}
												} /*
													 * end of individual
													 * question object
													 */
												surveyObj.setOfQuestionsInSurvey
														.push(question); /*
																			 * filling
																			 * question
																			 * object
																			 * into
																			 * array
																			 */
											}

											/*
											 * end of for loop for adding
											 * questions
											 */
											

											/*
											 * converting survey object to json
											 * format
											 */
											var surveyObjAsJSON = JSON
													.stringify(surveyObj);
										

											$
													.ajax({
														url : "/SurveyProjectMVC/rest/admin/addSurvey",
														type : "POST",
														data : surveyObjAsJSON,
														contentType : "application/json",
														success : function(response,status) {															
																window.location = "/SurveyProjectMVC/admin/AdminHome";					
															
														},
											error:function(error){
												alert(error.responseText);
												console.log(error);
												}
											});  //end of ajax call 

										} else
											alert("Invalid SurveyID or Name")
									}); // end of form submit function

					// logout function call

				}); // end of document.getready function
