<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Share Survey</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var admin_id =<%=adminid%>;
		
		var surveyID=${surveyId};
		console.log(surveyID)
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
								  "surveyId":${surveyId}
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
				  //ajax callstoring data in table
		$.ajax({
			url : "/SurveyProjectMVC/rest/admin/storeSharedSurveyDetails",
			type : "POST",
			contentType: "application/json",
			data : sharedSurveyWithUserAsJSON,
			success : function(response) {
				if(response=="success"){
					alert("Surveys shared successfully")
					 window.location= "/SurveyProjectMVC/admin/Administratorhome"; 
				}
				else{
					alert(response)
					 window.location= "/SurveyProjectMVC/admin/Administratorhome"; 
				}
			}
			
		
		});	//end of ajax for storign data in table
			
			
			
			
			
	 			 
			
		});
		
		
		//ajax call for loading all users
		$.ajax({
				url : "/SurveyProjectMVC/rest/admin/viewAllUsers?surveyId=${surveyId}",
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
		
				});	//end of ajax for retireving list of all users
				
				
	}); //end of dom
</script>
</head>
<body>
	<h1>Survey ID</h1>
	<form id="share_survey_form">
		<table id="tbody">
			<tr>
				<th>User ID:</th>
				<th>User Name:</th>
				<th>Share:</th>
			</tr>
		</table>
		<input type="submit" />
	</form>
</body>
</html>