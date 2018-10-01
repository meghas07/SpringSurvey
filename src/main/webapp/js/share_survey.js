/**
 * 
 */

	$(document).ready(function() {
		
		var sharedSurveyWithUsers=[];
		var sharedSurvey;
		
		$("#share_survey_form").submit(function(event){
			event.preventDefault();			
			$("input:checkbox:checked").each(function(){
				var userList=[];
				  userList.push($(this).attr("id").split("-")[1]);
				  for(user of userList){
					  sharedSurvey={
							  "survey":{
								  "surveyId":survey_id
							  }, 
					  "administrator":{
						  "id":admin_id	
						  },					  
					  "user":{
						  "id":parseInt(user)
					  }}
					
						  sharedSurveyWithUsers.push(sharedSurvey);
				  }
				 
			});
			
				  var sharedSurveyWithUserAsJSON=JSON.stringify(sharedSurveyWithUsers);
				  // ajax callstoring data in table
		$.ajax({
			url : "/SurveyProjectMVC/rest/admin/storeSharedSurveyDetails",
			type : "POST",
			contentType: "application/json",
			data : sharedSurveyWithUserAsJSON,
			success : function(response) {
				if(response=="success"){
					alert("Surveys shared successfully")
					 window.location= "/SurveyProjectMVC/admin/AdminHome"; 
				}
				else{
					alert(response)
					 window.location= "/SurveyProjectMVC/admin/AdminHome"; 
				}
			}
			
		
		});	// end of ajax for storign data in table
	
		});
		
		
		// ajax call for loading all users
		$.ajax({
				url : "/SurveyProjectMVC/rest/admin/viewAllUsers?surveyId="+survey_id,
				type : "GET",
				contentType: "application/json",
				success : function(response, status) {
					var count=0;
					for(user of response){    
						count++;
						var trChild=$('<tr></tr>');
		 				trChild.append('<td> '+user.id+'</td> <td>  '+user.name+'</td>');
		 			
		 				var	trButton=$('<td><input class="checkbox" type="checkbox" value='+user.id +' id="sharewith-'+user.id+'"/>Share</td>');
		 				trChild.append(trButton);
		 					$('#tbody').append(trChild);
		 				
					}
					console.log(count)
				}
		
				});	// end of ajax for retireving list of all users
				
				
	}); // end of dom
