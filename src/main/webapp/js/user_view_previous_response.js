/**
 * 
 */
$(document)
		.ready(
				function(event) {

					$
							.ajax({
								url : "/SurveyProjectMVC/rest/user/viewCompleteResponse?surveyId="
										+ survey_id,
								type : "GET",
								contentType : "application/json",
								success : function(response, status) {
									response
											.forEach(function(
													questionAndAnswer, index) {
												console.log(index);
												var question = questionAndAnswer.question.questionDescription;
												var answer = questionAndAnswer.response.response;
												var questionText = $('<p>'
														+ question
														+ '</p><br/>');
												var responsetext = $('<p>'
														+ answer + '</p><br/>');
												questionText
														.append(responsetext);
												$('#question_answer').append(
														questionText);
											});

								}

							});

				}); // end of dom function
