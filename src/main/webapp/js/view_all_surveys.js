/**
 * 
 */

$(document).ready(function() {
		
	
		 /* delete survey button */
		
		 function deleteSurvey(){
				var button_id= $(this).attr("id"); 			
				 console.log(button_id);			
				 $.ajax({
					 url:"/SurveyProjectMVC/rest/admin/deleteSurvey?surveyId="+survey.surveyId,
					 type : "GET",					
					success : function() {
							window.location="/SurveyProjectMVC/admin/viewAllSurvey";
						}
					 
				 });
				 
				 
			 }
	
		
		
		
		 // end of onclick for change status function
		
		 function changeStatus(){
				var button_id= $(this).attr("id");      
				
				 console.log(button_id);
				
				 $.ajax({
					 url:"/SurveyProjectMVC/rest/admin/changeSurveyStatus?surveyId="+button_id,
					
					 type : "GET",
					
					
					success : function(response, status) {
							console.log(response);
							if(response=="ACTIVE")
							$("#"+button_id).html(response).css("background-color", "#4CAF50");
							else
							$("#"+button_id).html(response).css("background-color", "#f44336");	
						}
					 
				 });
				 
				 
			 }
	
		 // for retrieving surveys from db
		 $.ajax({
			url : "/SurveyProjectMVC/rest/admin/viewAllSurveysCreated?adminId="+admin_id,
			type : "GET",
			contentType: "application/json",
			success : function(response, status) {
			
				for(survey of response){     
				 
					
						var viewBtn = $('<a href="/SurveyProjectMVC/admin/viewCompleteSurvey?surveyId=' + survey.surveyId +'" ><button>View Survey</button></a>');
						if( survey.status=="ACTIVE")
						var statusButton=$('<button class="survey_status" id='+survey.surveyId+' style="background-color: #4CAF50;"> '+ survey.status +'</button>').click(changeStatus);
						else
							var statusButton=$('<button class="survey_status" id='+survey.surveyId+' style="background-color: #f44336;"> '+ survey.status +'</button>').click(changeStatus);
						var trChild = $('<tr class="child"></tr>');
						var idTd = $('<td>'+survey.surveyId+'</td>');
						var nameTd = $('<td>'+survey.surveyName+'</td>');
						var statusTd = $('<td></td>').append(statusButton);
						var viewTd = $("<td></td>");
						var shareButton=$('<a href="/SurveyProjectMVC/admin/shareSurvey?surveyId=' + survey.surveyId +'"><button class="share_survey">Share Survey</button></a>');
						var shareTd = $('<td></td>').append(shareButton);
						var deleteTd= $('<td><button class="btn btn-danger btn-xs" id='+survey.surveyId+'><span class="glyphicon glyphicon-trash"></span></button></a></td>').click(deleteSurvey);
					 	   
						
						
						
						viewTd.append(viewBtn);
						
						trChild.append(idTd);
						trChild.append(nameTd);
						trChild.append(statusTd);
						trChild.append(viewTd);
						trChild.append(shareTd);
						
						trChild.append(deleteTd);
					
						$('#survey_tbl tbody').append(trChild);
				}
				
			},
		 error:function(error){
			 alert(error.responseText)
			 console.log(error)
		 }
		});
		
	// end of ajax for retireving surveys
	
	 $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#survey_tbl tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });

		 $("#home").click(function() {
				window.location = "/SurveyProjectMVC/admin/AdminHome";
			});
	}); // end of dom
