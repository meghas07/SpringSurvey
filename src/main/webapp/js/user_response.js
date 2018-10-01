/**
 * 
 */
  $(document).ready(function() {
			var count;
			var i ;
			var j;
			var questionIds=[];
			
            
            /* For retrieving survey questions */
            $.ajax({
                url: "/SurveyProjectMVC/rest/user/giveResponse?surveyId="+survey_id,
                type: "GET",
                contentType: "application/json",
                success: function(response, status) {
                	i = 0;
                	
                    for (question of response) { 
                        console.log(response);
                      questionIds.push(question.questionId);
                        var questionTd = $('<p>' + (i+1) + '. ' + question.questionDescription + '</p>  ');
                        var radioYesBtn = $('<input type="radio" name="answer' + question.questionId + '" value="yes" id="rbtnCountYes' + question.questionId + '" /><Label>Yes</Label>  ');
						var radioNoBtn =$('<input type="radio" name="answer' + question.questionId + '" value="no" id="rbtnCountNo' + question.questionId + '" /><Label>No</Label>');


                        $('#question_body ').append(questionTd);
                        $('#question_body ').append(radioYesBtn);
                        $('#question_body ').append(radioNoBtn);
                        i++;
                        console.log(i)
					
                    } // end of for
					
                 }

            });        
            // end of ajax for retrieving questions
           
            /* Storing responses in database */
            $("#response_form")
								.submit(
										function(event) {
											event.preventDefault();
										var listOfResponse=[];
										var responseObj;
										 var allVals = [];
										 function GetSelectedCheckBoxes() {
									         
									           for(var k=0;k<i;k++){
									            $( ' input[name=answer'+questionIds[k]+']:checked').each(function () {
									                allVals.push($(this).val());
									                
									            });
									            
									        }
									           alert(allVals); }
										 GetSelectedCheckBoxes();
										for (j = 0; j < i; j++){
											
											responseObj={
													"user":{
														"id":user_id
													},
													"question":{
														"questionId":questionIds[j] 
													},
													"survey":{
														"surveyId":survey_id 	   
													},
													"response":allVals[j]
											}
											
											listOfResponse.push(responseObj);
										}
										
										var listOfResponsesAsJSON= JSON
										.stringify(listOfResponse);
										/* ajax call for storing in database */
										 $
											.ajax({
												url : "/SurveyProjectMVC/rest/user/addResponse",
												type : "POST",
												data : listOfResponsesAsJSON,
												contentType : "application/json",
												success : function(
														response,
														status) {
													console.log(response)
													if (response == "success") {
														window.location = "/SurveyProjectMVC/user/userHome";
													} 
													else if(response=="fail!")
														{
														alert("You have already answered this survey.")
														window.location = "/SurveyProjectMVC/user/userHome";
														}													
													else
														alert("Unable to record your response please try again later");
													
												}// end of success function
											}); /* end of ajax call */
											
											});
        });